package ru.vtb.monitoring.vtb112.services.workers;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
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
public abstract class BaseSmWorker<T, K extends VmBaseResponseWrapper<T>, U extends BaseSmModel> {

    private final SmRepository<U> repository;
    private final UpdatesRepository updatesRepository;
    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    private final Class<K> vmModelWrapperType;
    private final Class<U> dbModelClassType;
    private final String workerName;
    private final String url;
    private final String smPort;

    public BaseSmWorker(AppConfig appConfig,
                        SmRepository<U> repository,
                        ModelMapper modelMapper,
                        UpdatesRepository updatesRepository,
                        Class<K> vmModelWrapperType,
                        Class<U> dbModelClassType,
                        String workerName,
                        String requestString,
                        HttpComponentsClientHttpRequestFactory httpRequestFactory) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.vmModelWrapperType = vmModelWrapperType;
        this.dbModelClassType = dbModelClassType;
        this.workerName = workerName;
        this.url = requestString;
        this.restTemplate = buildRestTemplate(appConfig, httpRequestFactory);
        this.smPort = appConfig.getSmPort();
    }

    private RestTemplate buildRestTemplate(AppConfig appConfig,
                                           HttpComponentsClientHttpRequestFactory httpRequestFactory) {
        RestTemplate rt = new RestTemplate(httpRequestFactory);
        rt.getInterceptors().add(new BasicAuthenticationInterceptor(appConfig.getSmLogin(), appConfig.getSmPassword()));
        return rt;
    }

    protected void process() {
        Updates update;
        ResponseEntity<K> response;

        try {
            update = updatesRepository.getUpdateEntityByServiceName(workerName);
            Map<String, Object> request = new HashMap<>();
            if (!Strings.isNullOrEmpty(smPort)){
                request.put("serverPort", smPort);
            }
            request.put("view", "expand");
            request.put("query",getQueryString(update));
            response = restTemplate.getForEntity(url, vmModelWrapperType, request);
        } catch (Exception exception) {
            log.error("Ошибка при передаче инцидента на сервис отправки уведомлений.", exception);
            return;
        }

        K body = response.getBody();

        if (body == null || !response.getStatusCode().is2xxSuccessful() || body.getReturnCode() > 0) {
            log.error(String.format("The SM service: %s returns response: %s", workerName, response.toString()));
            return;
        }

        List<T> resultBody = Arrays.stream(body.getContent())
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

        List<U> models = mapList(resultBody, dbModelClassType);

        models.stream()
                .max(Comparator.comparing(U::getUpdatedAt))
                .ifPresent(bm -> update.setUpdateTime(bm.getUpdatedAt()));

        repository.putModels(models);
        updatesRepository.putUpdate(update);
    }

    private String getQueryString(Updates update) {
        String dateTimeString = update.getUpdateTime().toString();
        String queryString = String.format("UpdatedAt>'%s'", dateTimeString);
        return UriEncoder.encode(queryString);
    }

    private List<U> mapList(List<T> source, Class<U> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
