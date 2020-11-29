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
                        String requestString) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.vmModelWrapperType = vmModelWrapperType;
        this.dbModelClassType = dbModelClassType;
        this.workerName = workerName;
        this.url = requestString;
        this.restTemplate = buildRestTemplate(appConfig.getSmUserLoginPass());
        this.smPort = appConfig.getSmPort();
    }

    private static RestTemplate buildRestTemplate(String smUserLoginPass) {
        String loginBasicEncoded = Base64.getEncoder().encodeToString(smUserLoginPass.getBytes());

        return new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic  " + loginBasicEncoded);
            return execution.execute(request, body);
        })).build();
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

            log.info("Try to load for service: {}, updateTime: {}, request: {}",
                    workerName,
                    update.getUpdateTime().toInstant(),
                    request
            );

            response = restTemplate.getForEntity(url, vmModelWrapperType, request);

            log.trace("Load data for service: {}, updateTime: {}, response: {}",
                    workerName,
                    update.getUpdateTime().toInstant(),
                    response
            );

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

        log.info("Put data to db for service: {}, new updateTime: {}",
                workerName,
                update.getUpdateTime().toInstant()
        );
    }

    private String getQueryString(Updates update) {
        String dateTimeString = update.getUpdateTime().toInstant().toString();
        String queryString = String.format("UpdatedAt>'%s'", dateTimeString);
        return UriEncoder.encode(queryString);
    }

    private List<U> mapList(List<T> source, Class<U> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
