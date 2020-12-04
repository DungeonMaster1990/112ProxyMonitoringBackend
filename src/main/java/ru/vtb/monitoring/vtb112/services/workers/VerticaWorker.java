package ru.vtb.monitoring.vtb112.services.workers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.pg.models.Metrics;
import ru.vtb.monitoring.vtb112.db.pg.models.SmDefMeasurementApi;
import ru.vtb.monitoring.vtb112.db.pg.models.SmRawdataMeasApi;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.MetricsRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmDefMeasurementApiRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.SmRawdataMeasApiRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmDefMeasurementVertica;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmDefMeasurementVerticaRepository;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmRawDataMeasVerticaRepository;
import ru.vtb.monitoring.vtb112.mappers.VerticaMapper;
import ru.vtb.monitoring.vtb112.services.helpers.interfaces.DateFormatterHelper;

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
@Transactional
public class VerticaWorker {
    private final SmDefMeasurementApiRepository smDefMeasurementApiRepository;
    private final SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository;
    private final SmRawdataMeasApiRepository smRawdataMeasApiRepository;
    private final SmRawDataMeasVerticaRepository smRawdataMeasVerticaRepository;
    private final MetricsRepository metricsRepository;
    private final UpdatesRepository updatesRepository;
    private final VerticaMapper verticaMapper;
    private final DateFormatterHelper dateFormatterHelper;

    @Value("${vertica.limit}")
    private Integer verticaLimit;
    @Value("${vertica.maxPages}")
    private Integer verticaMaxPages;

    @Autowired
    public VerticaWorker(SmDefMeasurementApiRepository smDefMeasurementApiRepository,
                         SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository,
                         SmRawdataMeasApiRepository smRawdataMeasApiRepository,
                         SmRawDataMeasVerticaRepository smRawdataMeasVerticaRepository,
                         MetricsRepository metricsRepository,
                         UpdatesRepository updatesRepository,
                         VerticaMapper verticaMapper,
                         DateFormatterHelper dateFormatterHelper) {
        this.smDefMeasurementApiRepository = smDefMeasurementApiRepository;
        this.smDefMeasurementVerticaRepository = smDefMeasurementVerticaRepository;
        this.smRawdataMeasApiRepository = smRawdataMeasApiRepository;
        this.smRawdataMeasVerticaRepository = smRawdataMeasVerticaRepository;
        this.metricsRepository = metricsRepository;
        this.updatesRepository = updatesRepository;
        this.verticaMapper = verticaMapper;
        this.dateFormatterHelper = dateFormatterHelper;
    }

    @Scheduled(fixedRateString = "${vertica.scheduler.fixedRate}")
    public void takeSmDefMeasurementVertica() {
        try {
            List<Metrics> metrics = metricsRepository.findByIsMerged(false);
            if (!metrics.isEmpty()) {
                List<SmDefMeasurementVertica> smDefMeasurementVerticaList =
                        smDefMeasurementVerticaRepository.getSmDefMeasurements(metrics);

                List<SmDefMeasurementApi> smDefMeasurementApiList =
                        smDefMeasurementVerticaList
                                .stream()
                                .map(verticaMapper::mapToSmDefMeasurementApi)
                                .collect(Collectors.toList());
                smDefMeasurementApiList.forEach(x-> {
                    x.setCreationDate(dateFormatterHelper.dbDateToZonedDate(x.getCreationDate(),"Europe/Moscow", "UTC"));
                    x.setModifiedDate(dateFormatterHelper.dbDateToZonedDate(x.getModifiedDate(),"Europe/Moscow", "UTC"));
                });
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
    public void takeSmRawDataMeasVertica() {
        Updates update = updatesRepository.getUpdateEntityByServiceName(WorkerName.VERTICA_SM_RAW_DATA.getName());
        List<Integer> measurementIds = metricsRepository.findByIsMerged(true)
                .stream()
                .map(Metrics::getMeasurementId)
                .collect(Collectors.toList());

        PriorityQueue<ZonedDateTime> maxTimestampPQ = new PriorityQueue<>(Comparator.reverseOrder());
        int curIteration = 0;
        while (curIteration++ < verticaMaxPages) {
            int processedRows = processSingleBatch(update, measurementIds,curIteration-1, maxTimestampPQ);
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

    private int processSingleBatch(Updates update,
                                   List<Integer> measurementIds,
                                   int pageNumber,
                                   PriorityQueue<ZonedDateTime> maxTimestampPQ) {

        List<SmRawdataMeasVertica> smRawDataMeasVerticaList =
                smRawdataMeasVerticaRepository.findByStatusIdAndTimeStampGreaterThanAndMeasurementIdIn(0,
                        update.getUpdateTime(),
                        measurementIds,
                        PageRequest.of(pageNumber, verticaLimit));

        List<SmRawdataMeasApi> smRawDataMeasApiList =
                smRawDataMeasVerticaList
                        .stream()
                        .map(verticaMapper::mapToSmRawdataMeasApi)
                        .collect(Collectors.toList());
        smRawDataMeasApiList.forEach(x-> {
                x.setDbdate(dateFormatterHelper.dbDateToZonedDate(x.getDbdate(), "Europe/Moscow","UTC"));
                x.setTimeStamp(dateFormatterHelper.dbDateToZonedDate(x.getTimeStamp(),"Europe/Moscow", "UTC"));
        });
        smRawdataMeasApiRepository.saveAll(smRawDataMeasApiList);

        maxTimestampPQ.addAll(smRawDataMeasVerticaList.stream()
                .map(SmRawdataMeasVertica::getTimeStamp)
                .collect(Collectors.toList()));

        return smRawDataMeasVerticaList.size();
    }
}
