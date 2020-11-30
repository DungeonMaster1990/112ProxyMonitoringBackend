package ru.vtb.monitoring.vtb112.services.workers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
import ru.vtb.monitoring.vtb112.db.vertica.models.SmDefMeasurementVertica;
import ru.vtb.monitoring.vtb112.db.vertica.models.SmRawdataMeasVertica;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmDefMeasurementVerticaRepository;
import ru.vtb.monitoring.vtb112.db.vertica.repositories.interfaces.SmRawdataMeasVerticaRepository;
import ru.vtb.monitoring.vtb112.mappers.VerticaMapper;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ConditionalOnProperty(value = "vertica.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
@Component
public class VerticaWorker {
    private final SmDefMeasurementApiRepository smDefMeasurementApiRepository;
    private final SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository;
    private final SmRawdataMeasApiRepository smRawdataMeasApiRepository;
    private final SmRawdataMeasVerticaRepository smRawdataMeasVerticaRepository;
    private final MetricsRepository metricsRepository;
    private final UpdatesRepository updatesRepository;
    private final VerticaMapper verticaMapper;

    @Autowired
    public VerticaWorker(SmDefMeasurementApiRepository smDefMeasurementApiRepository, SmDefMeasurementVerticaRepository smDefMeasurementVerticaRepository, SmRawdataMeasApiRepository smRawdataMeasApiRepository, SmRawdataMeasVerticaRepository smRawdataMeasVerticaRepository, MetricsRepository metricsRepository, UpdatesRepository updatesRepository, VerticaMapper verticaMapper) {
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
    public void takeSmRawdataMeasVertica() {
        try {
            String verticaServiceName = "VerticaSmRawData";
            Updates update = updatesRepository.getUpdateEntityByServiceName(verticaServiceName);
            List<SmRawdataMeasVertica> smRawdataMeasVerticaList =
                    smRawdataMeasVerticaRepository.getSmRawdataMeasVertica(update);
            List<SmRawdataMeasApi> smRawdataMeasApiList =
                    smRawdataMeasVerticaList
                            .stream()
                            .map(verticaMapper::mapToSmRawdataMeasApi)
                            .collect(Collectors.toList());
            smRawdataMeasApiRepository.saveAll(smRawdataMeasApiList);

            smRawdataMeasVerticaList.stream()
                    .max(Comparator.comparing(SmRawdataMeasVertica::getTimeStamp))
                    .map(SmRawdataMeasVertica::getTimeStamp)
                    .ifPresent(update::setUpdateTime);

            updatesRepository.putUpdate(update);
        } catch (SQLException sqlException) {
            log.error("Произошла ошибка при попытке выгрузки данных из Vertic-и из таблицы SmRawdataMeas", sqlException);
        }
    }
}
