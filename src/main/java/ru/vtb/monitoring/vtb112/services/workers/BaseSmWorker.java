package ru.vtb.monitoring.vtb112.services.workers;

import antlr.StringUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.BaseSmModel;
import ru.vtb.monitoring.vtb112.db.models.Updates;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.SmRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmBaseResponseWrapper;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmModelWrapper;

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
    private String url;

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
        this.url = requestString;
    }

    protected void process() {
        Updates update;
        ResponseEntity<TT> response;

        try {
            RestTemplate restTemplate = buildRestTemplate();
            update = updatesRepository.getUpdateEntityByServiceName(workerName);

            Map<String, Object> request = new HashMap<>();
            if (!Strings.isNullOrEmpty(appConfig.getSmPort())){
                request.put("serverPort", appConfig.getSmPort());
            }

            request.put("view", "expand");
            request.put("query",getQueryString(update));

            response = restTemplate.getForEntity(url, vmModelWrapperType, request);
        }
        catch(Exception exception){
            log.error("Ошибка при передаче инцидента на сервис отправки уведомлений.", exception);
            return;
        }

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null || response.getBody().getReturnCode() > 0){
            log.error(String.format("The SM service: %s returns response: %s", workerName, response.toString()));;
            return;
        }

        List<T> resultBody = Arrays.stream(response.getBody().getContent())
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

        List<U> models = mapList(resultBody, dbModelClassType);

        models.stream()
                .max(Comparator.comparing(U::getUpdatedAt))
                .ifPresent(bm -> update.setUpdateTime(bm.getUpdatedAt()));

        repository.putModels(models);
        updatesRepository.putUpdate(update);
    }

    private RestTemplate buildRestTemplate(){
        String loginBasicEncoded = Base64.getEncoder().encodeToString(appConfig.getSmUserLoginPass().getBytes());

        RestTemplate restTemplate = new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic  " + loginBasicEncoded);
            return execution.execute(request, body);
        })).build();

        return restTemplate;
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
