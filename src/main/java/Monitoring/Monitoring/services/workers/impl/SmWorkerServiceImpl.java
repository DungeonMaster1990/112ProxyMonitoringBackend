package Monitoring.Monitoring.services.workers.impl;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.VtbIncidents;
import Monitoring.Monitoring.db.repositories.interfaces.VtbIncidentsRepository;
import Monitoring.Monitoring.db.repositories.interfaces.VtbUnavailabilityRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.VmVtbIncidentResponse;
import Monitoring.Monitoring.dto.services.viewmodels.response.VmVtbUnavailabilityResponse;
import Monitoring.Monitoring.services.workers.interfaces.SmWorkerService;
import io.swagger.models.Model;
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
    private VtbIncidentsRepository vtbIncidentsRepository;
    private VtbUnavailabilityRepository vtbUnavailabilityRepository;

    @Autowired
    private SmWorkerServiceImpl(
            AppConfig appConfig,
            VtbIncidentsRepository vtbIncidentsRepository,
            VtbUnavailabilityRepository vtbUnavailabilityRepository)
    {
        this.vtbIncidentsRepository = vtbIncidentsRepository;
        this.appConfig = appConfig;
        this.modelMapper = new ModelMapper();
        this.vtbUnavailabilityRepository = vtbUnavailabilityRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void takeDataFromSm() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        var incidents = saveIncidentsFromSm();
        var unavailabilities = getVtbUnavailabilitiesFromSm(incidents);
        //var unavalabilitiesFromDb = getVtbUnavailabilitiesFromDb
    }

    private List<VtbIncidents> saveIncidentsFromSm() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VmVtbIncidentResponse[]> response = restTemplate.getForEntity(getVtbIncidentsRequestString(), VmVtbIncidentResponse[].class);
        if (!response.getStatusCode().is2xxSuccessful()){
            throw new Exception("The SM service didn't respond for Incident Request");
        }

        var incidents = saveVtbIncidentsFromResponse(response);

        return incidents;
    }

    private void getVtbIncidentsToUpdate(){
        var incidents = vtbIncidentsRepository.getTimeFilteredVtbIncidents(appConfig.getDeepDays());
    }

    private String getVtbIncidentsRequestString(){
        return appConfig.getSmIncidentUrl();
    }

    private List<VtbIncidents> saveVtbIncidentsFromResponse(@NotNull ResponseEntity<VmVtbIncidentResponse[]> response){
        var vtbIncidents = response.getBody();
        ArrayList<VtbIncidents> incidents = new ArrayList<VtbIncidents>();
        var incidentsDTO = mapArray(vtbIncidents, VtbIncidents.class);
        var incidentsForeignIds = incidentsDTO.stream()
                .map(incedent -> incedent.getIncidentId())
                .collect(Collectors.toList());

        var oldVtbIncidents = vtbIncidentsRepository.getVtbIncidents(incidentsForeignIds)
                .stream()
                .collect(Collectors.toMap(VtbIncidents::getIncidentId, VtbIncidents::getId));

        for (var incident : incidentsDTO){
            var foreignIncidentId = incident.getIncidentId();
            if (oldVtbIncidents.containsKey(foreignIncidentId))
                incident.setId(oldVtbIncidents.get(foreignIncidentId));
        }
        vtbIncidentsRepository.putVtbIncidents(incidentsDTO);

        return incidentsDTO;
    }

    private List<VmVtbUnavailabilityResponse> getVtbUnavailabilitiesFromSm(List<VtbIncidents> incidents) throws Exception {
        ArrayList<VmVtbUnavailabilityResponse> results = new ArrayList<>();

        for(var incident : incidents){
            var result = getVtbUnavailabilityFromSm(incident);
            results.add(result);
        }
        return results;
    }

    private VmVtbUnavailabilityResponse getVtbUnavailabilityFromSm(VtbIncidents incident) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VmVtbUnavailabilityResponse> response = restTemplate.getForEntity(getVtbUnavailabilityRequestString(incident), VmVtbUnavailabilityResponse.class);
        if (!response.getStatusCode().is2xxSuccessful()){
            throw new Exception("The SM service didn't respond for Unavailability Request");
        }
        return response.getBody();
    }

    private String getVtbUnavailabilityRequestString(VtbIncidents incident){
        return appConfig.getSmIncidentUrl();
    }


    <S, T> List<T> mapArray(S[] source, Class<T> targetClass) {
        return Arrays.stream(source)
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
