package Monitoring.Monitoring.services.workers.impl;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Incidents;
import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentsRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UnavailabilityRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.VmIncidentResponse;
import Monitoring.Monitoring.dto.services.viewmodels.response.VmUnavailabilityResponse;
import Monitoring.Monitoring.services.workers.interfaces.SmWorkerService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SmWorkerServiceImpl implements SmWorkerService {

    private AppConfig appConfig;
    private ModelMapper modelMapper;
    private IncidentsRepository incidentsRepository;
    private UnavailabilityRepository unavailabilityRepository;

    @Autowired
    private SmWorkerServiceImpl(
            AppConfig appConfig,
            IncidentsRepository incidentsRepository,
            UnavailabilityRepository unavailabilityRepository)
    {
        this.incidentsRepository = incidentsRepository;
        this.appConfig = appConfig;
        this.modelMapper = new ModelMapper();
        this.unavailabilityRepository = unavailabilityRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void takeDataFromSm() throws Exception {
        var incidents = saveIncidentsFromSm();
        var unavailabilities = getUnavailabilitiesFromSm(incidents);
        saveUnavailabilities(unavailabilities);
    }

    private List<Incidents> saveIncidentsFromSm() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VmIncidentResponse[]> response = restTemplate.getForEntity(getVtbIncidentsRequestString(), VmIncidentResponse[].class);
        if (!response.getStatusCode().is2xxSuccessful()){
            throw new Exception("The SM service didn't respond for Incident Request");
        }

        var incidents = saveVtbIncidentsFromResponse(response);

        return incidents;
    }

    private void getVtbIncidentsToUpdate(){
        var incidents = incidentsRepository.getTimeFilteredVtbIncidents(appConfig.getDeepDays());
    }

    private String getVtbIncidentsRequestString(){
        return appConfig.getSmIncidentUrl();
    }

    private List<Incidents> saveVtbIncidentsFromResponse(@NotNull ResponseEntity<VmIncidentResponse[]> response){
        var vtbIncidents = response.getBody();
        ArrayList<Incidents> incidents = new ArrayList<Incidents>();
        var incidentsDTO = mapArray(vtbIncidents, Incidents.class);
        var incidentsForeignIds = incidentsDTO.stream()
                .map(incedent -> incedent.getIncidentId())
                .collect(Collectors.toList());

        var oldVtbIncidents = incidentsRepository.getVtbIncidents(incidentsForeignIds)
                .stream()
                .collect(Collectors.toMap(Incidents::getIncidentId, Incidents::getId));

        for (var incident : incidentsDTO){
            var foreignIncidentId = incident.getIncidentId();
            if (oldVtbIncidents.containsKey(foreignIncidentId))
                incident.setId(oldVtbIncidents.get(foreignIncidentId));
        }
        incidentsRepository.putVtbIncidents(incidentsDTO);

        return incidentsDTO;
    }

    private List<Unavailabilities> getUnavailabilitiesFromSm(List<Incidents> incidents) throws Exception {
        ArrayList<VmUnavailabilityResponse> results = new ArrayList<>();

        for(var incident : incidents){
            var result = getUnavailabilityFromSm(incident);
            results.add(result);
        }

        return mapList(results, Unavailabilities.class);
    }

    private VmUnavailabilityResponse getUnavailabilityFromSm(Incidents incident) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VmUnavailabilityResponse> response = restTemplate.getForEntity(getUnavailabilityRequestString(incident), VmUnavailabilityResponse.class);
        if (!response.getStatusCode().is2xxSuccessful()){
            throw new Exception("The SM service didn't respond for Unavailability Request");
        }
        return response.getBody();
    }

    private String getUnavailabilityRequestString(Incidents incident){
        return appConfig.getSmIncidentUrl();
    }

    private void saveUnavailabilities(List<Unavailabilities> unavailabilities){
        var faultIds = unavailabilities
                .stream()
                .map(Unavailabilities::getFaultId)
                .toArray(String[]::new);
        var serviceIds = unavailabilities
                .stream()
                .map(Unavailabilities::getServiceId)
                .toArray(String[]::new);

        var unavailabilitiesFromDb = unavailabilityRepository.getVtbUnavailabilities(faultIds, serviceIds);

        for (var unavailability : unavailabilities){
            for (var unavailabilityFromDb : unavailabilitiesFromDb){
                if (unavailability.getFaultId() == unavailabilityFromDb.getFaultId()
                        && unavailability.getServiceId() == unavailabilityFromDb.getServiceId()){
                    var id = unavailabilityFromDb.getId();
                    unavailability.setId(id);
                }
            }
        }
        unavailabilityRepository.putVtbUnavailabilities(unavailabilities);
    }


    <S, T> List<T> mapArray(S[] source, Class<T> targetClass) {
        return Arrays.stream(source)
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
