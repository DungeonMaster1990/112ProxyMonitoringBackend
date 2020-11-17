package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.BaseSmModel;
import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.repositories.interfaces.SmRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmModelWrapper;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmBaseResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseSmWorker <T, TT extends VmBaseResponseWrapper<T>, U extends BaseSmModel> {

    private AppConfig appConfig;
    private SmRepository<U> repository;
    private UpdatesRepository updatesRepository;
    private ModelMapper modelMapper;

    private Class<TT> vmModelWrapperType;
    private Class<U> dbModelClassType;
    private String workerName;
    private String requestString;


    public BaseSmWorker(
            AppConfig appConfig,
            SmRepository<U> repository,
            ModelMapper modelMapper,
            UpdatesRepository updatesRepository,
            Class<TT> vmModelWrapperType,
            Class<U> dbModelClassType,
            String workerName,
            String requestString){
        this.appConfig = appConfig;
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.vmModelWrapperType = vmModelWrapperType;
        this.dbModelClassType = dbModelClassType;
        this.workerName = workerName;
        this.requestString = requestString;
    }

    protected void process() {

        String loginBasicEncoded = Base64.getEncoder().encodeToString(appConfig.getSmUserLoginPass().getBytes());

        RestTemplate restTemplate = new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic  " + loginBasicEncoded);
            return execution.execute(request, body);
        })).build();

        Updates update = updatesRepository.getUpdateEntityByServiceName(workerName);

        Map<String, Object> request = Map.of(
                "view", "expand" ,
                "query", getQueryString(update));


        ResponseEntity<TT> response = restTemplate.getForEntity(requestString, vmModelWrapperType, request);

//        if (!response.getStatusCode().is2xxSuccessful()){
//            throw new Exception("The SM service didn't respond for Incident Request");
//        }

        List<T> resultBody = Arrays.stream(response.getBody().getContent())
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

        List<U> models = mapList(resultBody, dbModelClassType);

        ZonedDateTime updatedAt = models.stream()
                .max(Comparator.comparing(U::getUpdatedAt))
                .get()
                .getUpdatedAt();

        update.setUpdateTime(updatedAt);
        repository.putModels(models);
        updatesRepository.putUpdate(update);
    }

    private String getQueryString(Updates update){
        String dateTimeString = update.getUpdateTime().toString();
        String queryString = String.format("UpdatedAt>'%s'", dateTimeString);
        String result = UriEncoder.encode(queryString);
        return result;
    }

    <SS, TT> List<TT> mapList(List<SS> source, Class<TT> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
