package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmIncident;
import ru.vtb.monitoring.vtb112.mappers.IncidentMapper;

@ConditionalOnProperty(value = "sm.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SmIncidentsWorker extends BaseSmWorker<VmSmIncident, Incident> {

    SmIncidentsWorker(AppConfig appConfig,
                      IncidentRepository incidentRepository,
                      IncidentMapper incidentMapper,
                      UpdatesRepository updatesRepository) {
        super(incidentRepository,
                incidentMapper,
                updatesRepository,
                WorkerName.SM_INCIDENTS,
                appConfig.getSmIncidentUrl());
    }

    @Scheduled(fixedRateString = "${sm.scheduler.fixedRate}")
    public void loadIncidentsFromSm() {
        process();
    }
}
