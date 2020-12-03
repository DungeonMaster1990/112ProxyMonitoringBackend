package ru.vtb.monitoring.vtb112.services.workers;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.models.BaseSmModel;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmBaseResponseWrapper;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmModelWrapper;
import ru.vtb.monitoring.vtb112.mappers.ResponseMapper;

import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseSmWorker<T, K extends VmBaseResponseWrapper<T>, U extends BaseSmModel> {

    private final SmRepository<U> repository;
    private final UpdatesRepository updatesRepository;
    private final ResponseMapper<U, T> responseMapper;

    private final Class<K> vmModelWrapperType;
    private final String workerName;
    private final String url;
    private final RestTemplate restTemplate;

    public BaseSmWorker(AppConfig appConfig,
                        SmRepository<U> repository,
                        ResponseMapper<U, T> responseMapper,
                        UpdatesRepository updatesRepository,
                        Class<K> vmModelWrapperType,
                        String workerName,
                        String requestString) {
        this.responseMapper = responseMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.vmModelWrapperType = vmModelWrapperType;
        this.workerName = workerName;
        var serverPort = Strings.isNullOrEmpty(appConfig.getSmPort()) ? "?" : ("?serverPort=" + appConfig.getSmPort() + '&');
        this.url = requestString + serverPort + "view={view}&query={query}";
        this.restTemplate = buildRestTemplate(appConfig.getSmUserLoginPass());
    }

    private static RestTemplate buildRestTemplate(String smUserLoginPass) {
        String loginBasicEncoded = Base64.getEncoder().encodeToString(smUserLoginPass.getBytes());

        return new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic " + loginBasicEncoded);
            return execution.execute(request, body);
        })).build();
    }

    protected void process() {
        Updates update;
        ResponseEntity<K> response;

        try {
            update = updatesRepository.getUpdateEntityByServiceName(workerName);
            var updateTime = update.getUpdateTime().toInstant();

            log.info("Try to load for service: {}, updateTime: {}, request: {}",
                    workerName,
                    updateTime,
                    url
            );

            response = restTemplate.getForEntity(url, vmModelWrapperType, "expand", buildQuery(updateTime));

            log.debug("Load data for service: {}, updateTime: {}, response: {}",
                    workerName,
                    updateTime,
                    response
            );
        } catch (Exception exception) {
            log.error("Exception during process in worker {}", workerName, exception);
            return;
        }
        K body = response.getBody();

        if (body == null || !response.getStatusCode().is2xxSuccessful() || body.getReturnCode() > 0) {
            log.error(String.format("The SM service: %s returns response: %s", workerName, response.toString()));
            return;
        }

        if (body.getContent() == null || body.getContent().length == 0){
            log.debug("From sm service {} was loaded 0 rows", workerName);
            return;
        }

        log.debug("From sm service {} was loaded {} entities", workerName, body.getContent().length);

        List<T> result = Arrays.stream(body.getContent())
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

        List<U> models = result.stream()
                .map(responseMapper::mapToResponse)
                .collect(Collectors.toList());

        models.stream()
                .max(Comparator.comparing(U::getUpdatedAt))
                .ifPresent(bm -> update.setUpdateTime(bm.getUpdatedAt()));

        repository.putModels(models);
        updatesRepository.putUpdate(update);

        log.info("Put data to db for sm service: {}, new updateTime: {}",
                workerName,
                update.getUpdateTime().toInstant()
        );
    }

    private String buildQuery(Instant updateTime) {
        String dateTimeString = updateTime.toString();
        return String.format("UpdatedAt>'%s'", dateTimeString);
    }
}
