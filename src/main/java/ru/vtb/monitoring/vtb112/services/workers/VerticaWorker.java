package ru.vtb.monitoring.vtb112.services.workers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.models.Metrics;
import ru.vtb.monitoring.vtb112.db.models.SmDefMeasurementApi;
import ru.vtb.monitoring.vtb112.db.models.SmRawdataMeasApi;
import ru.vtb.monitoring.vtb112.db.models.Updates;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.MetricsRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.SmDefMeasurementApiRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.SmRawdataMeasApiRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.vertica.models.SmDefMeasurementVertica;
import ru.vtb.monitoring.vtb112.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.vertica.repositories.interfaces.SmDefMeasurementVerticaRepository;
import ru.vtb.monitoring.vtb112.vertica.repositories.interfaces.SmRawDataMeasVerticaRepository;
import ru.vtb.monitoring.vtb112.mappers.VerticaMapper;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@ConditionalOnProperty(value = "vertica.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
@Component
public class VerticaWorker {
    private final SmDefMeasurementApiRepository smDefMeasurementApiRepository;
    private final SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository;
    private final SmRawdataMeasApiRepository smRawdataMeasApiRepository;
    private final SmRawDataMeasVerticaRepository smRawdataMeasVerticaRepository;
    private final MetricsRepository metricsRepository;
    private final UpdatesRepository updatesRepository;
    private final VerticaMapper verticaMapper;

    @Value("${vertica.limit}")
    private Integer verticaLimit;

    @Autowired
    public VerticaWorker(SmDefMeasurementApiRepository smDefMeasurementApiRepository, SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository, SmRawdataMeasApiRepository smRawdataMeasApiRepository, SmRawDataMeasVerticaRepository smRawdataMeasVerticaRepository, MetricsRepository metricsRepository, UpdatesRepository updatesRepository, VerticaMapper verticaMapper) {
        this.smDefMeasurementApiRepository = smDefMeasurementApiRepository;
        this.smDefMeasurementVerticaRepository = smDefMeasurementVerticaRepository;
        this.smRawdataMeasApiRepository = smRawdataMeasApiRepository;
        this.smRawdataMeasVerticaRepository = smRawdataMeasVerticaRepository;
        this.metricsRepository = metricsRepository;
        this.updatesRepository = updatesRepository;
        this.verticaMapper = verticaMapper;
    }

    @Scheduled(fixedRateString = "${vertica.scheduler.fixedRate}")
    @Transactional
    public void takeSmDefMeasurementVertica() {
        try {
            List<Metrics> metrics = metricsRepository.findAll()
                    .stream()
                    .filter(m -> !m.isMerged())
                    .collect(Collectors.toList());
            if (!metrics.isEmpty()) {
                List<SmDefMeasurementVertica> smDefMeasurementVerticaList =
                        smDefMeasurementVerticaRepository.getSmDefMeasurements(metrics);

                List<SmDefMeasurementApi> smDefMeasurementApiList =
                        smDefMeasurementVerticaList
                                .stream()
                                .map(verticaMapper::mapToSmDefMeasurementApi)
                                .collect(Collectors.toList());

                smDefMeasurementApiRepository.saveAll(smDefMeasurementApiList);

                for (Metrics metric : metrics) {
                    metric.setMerged(true);
                }
                metricsRepository.saveAll(metrics);
            }
        } catch (SQLException sqlException) {
            log.error("Произошла ошибка при попытке выгрузки данных из Vertic-и из таблицы SmDefMeasurements", sqlException);
        }
    }

    @Scheduled(fixedRateString = "${vertica.scheduler.fixedRate}")
    @Transactional
    public void takeSmRawDataMeasVertica() {

        String verticaServiceName = "VerticaSmRawData";
        Updates update = updatesRepository.getUpdateEntityByServiceName(verticaServiceName);

        PriorityQueue<ZonedDateTime> maxTimestampPQ = new PriorityQueue<>(Comparator.reverseOrder());
        int maxIterations = 100;
        int curIteration = 0;
        while (curIteration++ < maxIterations) {
            int processedRows = processSingleBatch(update, curIteration-1, maxTimestampPQ);
            log.info("Processed rows {} on iteration # {}", processedRows, curIteration);
            if (processedRows < verticaLimit) {
                break;
            }
        }
        Optional.ofNullable(maxTimestampPQ.peek())
                .ifPresent(maxTimestamp -> {
                    update.setUpdateTime(maxTimestamp);
                    updatesRepository.putUpdate(update);
                });
    }

    @Transactional
    private int processSingleBatch(Updates update,
                                   int pageNumber,
                                   PriorityQueue<ZonedDateTime> maxTimestampPQ) {

        List<SmRawdataMeasVertica> smRawDataMeasVerticaList =
                smRawdataMeasVerticaRepository.findByStatusIdAndTimeStampGreaterThan(0,
                        update.getUpdateTime(),
                        PageRequest.of(pageNumber, verticaLimit));
        List<SmRawdataMeasApi> smRawDataMeasApiList =
                smRawDataMeasVerticaList
                        .stream()
                        .map(verticaMapper::mapToSmRawdataMeasApi)
                        .collect(Collectors.toList());
        smRawdataMeasApiRepository.saveAll(smRawDataMeasApiList);

        maxTimestampPQ.addAll(smRawDataMeasVerticaList.stream()
                .map(SmRawdataMeasVertica::getTimeStamp)
                .collect(Collectors.toList()));

        return smRawDataMeasVerticaList.size();
    }
}
