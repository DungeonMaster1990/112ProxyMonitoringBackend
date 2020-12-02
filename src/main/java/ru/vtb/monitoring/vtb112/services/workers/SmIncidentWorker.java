package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmIncidentWrapper;
import ru.vtb.monitoring.vtb112.mappers.IncidentMapper;

@ConditionalOnProperty(value = "sm.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SmIncidentWorker extends BaseSmWorker<VmSmIncident, VmIncidentWrapper, Incident> {

    SmIncidentWorker(AppConfig appConfig,
                     IncidentRepository incidentRepository,
                     IncidentMapper incidentMapper,
                     UpdatesRepository updatesRepository) {
        super(appConfig,
                incidentRepository,
                incidentMapper,
                updatesRepository,
                VmIncidentWrapper.class,
                "Incidents",
                appConfig.getSmIncidentUrl());
    }

    @Scheduled(fixedRateString = "${sm.scheduler.fixedRate}")
    public void loadIncidentsFromSm() {
        process();
    }
}
