package ru.vtb.monitoring.vtb112.services.workers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.vtb.monitoring.vtb112.db.pg.models.BaseSmModel;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmBaseModel;
import ru.vtb.monitoring.vtb112.dto.sm.response.wrappers.VmBaseResponseWrapper;
import ru.vtb.monitoring.vtb112.mappers.ResponseMapper;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseSmWorker<T extends VmBaseModel, U extends BaseSmModel> {

    @Getter(AccessLevel.PROTECTED)
    private final SmRepository<U> repository;
    private final UpdatesRepository updatesRepository;
    private final ResponseMapper<U, T> responseMapper;

    private final String workerName;
    private final String url;

    @Value("${sm.limit-per-request}")
    private Integer countPerRequest;
    @Value("${sm.max-pages}")
    private Integer smMaxPages;
    @Value("${sm.port:}")
    private Integer smPort;

    @Autowired
    @Qualifier("smRestTemplate")
    private RestTemplate restTemplate;

    protected BaseSmWorker(SmRepository<U> repository,
                           ResponseMapper<U, T> responseMapper,
                           UpdatesRepository updatesRepository,
                           WorkerName workerName,
                           String requestString) {
        this.responseMapper = responseMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.workerName = workerName.getName();
        this.url = requestString;
    }

    protected void process() {
        Updates update = updatesRepository.getUpdateEntityByServiceName(workerName);

        PriorityQueue<ZonedDateTime> maxTimestampPQ = new PriorityQueue<>(Comparator.reverseOrder());
        int curIteration = 0;
        int totalRowsProcessed = 0;
        int iterationProcessedRows = countPerRequest;
        while (curIteration++ < smMaxPages && iterationProcessedRows == countPerRequest) {
            try {
                iterationProcessedRows = processSinglePage(update, curIteration, maxTimestampPQ);
                totalRowsProcessed += iterationProcessedRows;
            } catch (Exception exception) {
                log.error("Exception during process in worker {}", workerName, exception);
                return;
            }
            log.info("Processed rows {} on iteration # {} by {} worker",
                    iterationProcessedRows, curIteration, workerName);
        }
        Optional.ofNullable(maxTimestampPQ.peek())
                .ifPresent(maxTimestamp -> {
                    update.setUpdateTime(maxTimestamp);
                    updatesRepository.putUpdate(update);
                });
        updatesRepository.putUpdate(update);

        log.info("Put {} items to db for sm service: {}, new updateTime: {}", totalRowsProcessed, workerName,
                update.getUpdateTime().toInstant()
        );
    }

    private int processSinglePage(Updates update, int pageNumber, PriorityQueue<ZonedDateTime> maxTimestampPQ) {
        var updateTime = update.getUpdateTime().toInstant();
        var requestUrl = SmUtils.buildUrl(url, smPort, updateTime, pageNumber, countPerRequest);

        log.debug("Try to load for service: {}, updateTime: {}, request: {}",
                workerName, updateTime, requestUrl);

        ResponseEntity<VmBaseResponseWrapper<T>> response = restTemplate.exchange(requestUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {});

        log.debug("Load data for service: {}, updateTime: {}, response: {}",
                workerName, updateTime, response);

        VmBaseResponseWrapper<T> body = response.getBody();

        if (body == null || !response.getStatusCode().is2xxSuccessful() || body.getReturnCode() > 0) {
            log.error("The SM service: {} returns response: {}", workerName, response.toString());
            return 0;
        }
        if (body.getContent() == null || body.getContent().isEmpty()) {
            log.debug("From sm service {} was loaded 0 rows", workerName);
            return 0;
        }
        log.debug("From sm service {} was loaded {} entities", workerName, body.getContent().size());

        List<U> models = body.getContent().stream()
                .map(modelWrapper -> responseMapper.mapToResponse(modelWrapper.getModel()))
                .peek(model -> {
                    if (model.getUpdatedAt() != null) {
                        maxTimestampPQ.add(model.getUpdatedAt());
                    }
                })
                .collect(Collectors.toList());
        repository.putModels(models);

        return models.size();
    }
}
