package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmIncidentWrapper;
import ru.vtb.monitoring.vtb112.mappers.wrappers.IncidentMapperWrapper;

import javax.transaction.NotSupportedException;

@Component
public class SmIncidentWorker extends BaseSmWorker<VmSmIncident, VmIncidentWrapper, Incident> {

    @Autowired
    private SmIncidentWorker(
            AppConfig appConfig,
            IncidentRepository incidentRepository,
            IncidentMapperWrapper incidentMapperWrapper,
            UpdatesRepository updatesRepository)
    {
        super(appConfig,
                incidentRepository,
                incidentMapperWrapper,
                updatesRepository,
                VmSmIncident.class,
                VmIncidentWrapper.class,
                Incident.class,
                "Incidents",
                appConfig.getSmIncidentUrl());
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadIncidentsFromSm() throws NotSupportedException {
        process();
    }
}
