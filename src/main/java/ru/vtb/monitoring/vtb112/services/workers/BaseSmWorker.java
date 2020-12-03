package ru.vtb.monitoring.vtb112.services.workers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.vtb.monitoring.vtb112.db.pg.models.BaseSmModel;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmBaseModel;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmBaseResponseWrapper;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmModelWrapper;
import ru.vtb.monitoring.vtb112.mappers.ResponseMapper;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseSmWorker<T extends VmBaseModel, U extends BaseSmModel> {

    @Getter(AccessLevel.PROTECTED)
    private final SmRepository<U> repository;
    private final UpdatesRepository updatesRepository;
    private final ResponseMapper<U, T> responseMapper;

    private final String workerName;
    private final String url;

    @Autowired
    @Qualifier("smRestTemplate")
    private RestTemplate restTemplate;

    protected BaseSmWorker(Integer smPort,
                           SmRepository<U> repository,
                           ResponseMapper<U, T> responseMapper,
                           UpdatesRepository updatesRepository,
                           WorkerName workerName,
                           String requestString) {
        this.responseMapper = responseMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.workerName = workerName.getName();
        this.url = buildUrl(smPort, requestString);
    }

    protected static String buildQuery(Instant updateTime) {
        String dateTimeString = updateTime.toString();
        return String.format("UpdatedAt>'%s'", dateTimeString);
    }

    protected static String buildUrl(Integer smPort, String requestString) {
        var serverPort = smPort == null ? "?" : ("?serverPort=" + smPort + '&');
        return requestString + serverPort + "view={view}&query={query}";
    }

    protected void process() {
        Updates update;
        ResponseEntity<VmBaseResponseWrapper<T>> response;

        try {
            update = updatesRepository.getUpdateEntityByServiceName(workerName);
            var updateTime = update.getUpdateTime().toInstant();

            log.info("Try to load for service: {}, updateTime: {}, request: {}",
                    workerName,
                    updateTime,
                    url
            );
            response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            }, "expand", buildQuery(updateTime));

            log.debug("Load data for service: {}, updateTime: {}, response: {}",
                    workerName,
                    updateTime,
                    response
            );
        } catch (Exception exception) {
            log.error("Exception during process in worker {}", workerName, exception);
            return;
        }
        VmBaseResponseWrapper<T> body = response.getBody();

        if (body == null || !response.getStatusCode().is2xxSuccessful() || body.getReturnCode() > 0) {
            log.error(String.format("The SM service: %s returns response: %s", workerName, response.toString()));
            return;
        }

        if (body.getContent() == null || body.getContent().isEmpty()) {
            log.debug("From sm service {} was loaded 0 rows", workerName);
            return;
        }

        log.debug("From sm service {} was loaded {} entities", workerName, body.getContent().size());

        List<T> result = body.getContent().stream()
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

        List<U> models = result.stream()
                .map(responseMapper::mapToResponse)
                .collect(Collectors.toList());

        models.stream()
                .filter(u -> u.getUpdatedAt() != null)
                .max(Comparator.comparing(U::getUpdatedAt))
                .ifPresent(bm -> update.setUpdateTime(bm.getUpdatedAt()));

        repository.putModels(models);
        updatesRepository.putUpdate(update);

        log.info("Put data to db for sm service: {}, new updateTime: {}",
                workerName,
                update.getUpdateTime().toInstant()
        );
    }
}
