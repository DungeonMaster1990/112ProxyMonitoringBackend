package Monitoring.Monitoring.services.workers.impl;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Incidents;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentsRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmIncidentWrapper;
import Monitoring.Monitoring.services.workers.BaseSmWorker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SmIncidentWorkerImpl extends BaseSmWorker<VmSmIncident, VmIncidentWrapper, Incidents> {

    @Autowired
    private SmIncidentWorkerImpl(
            AppConfig appConfig,
            IncidentsRepository incidentsRepository,
            UpdatesRepository updatesRepository)
    {
        super(appConfig,
                incidentsRepository,
                new ModelMapper(),
                updatesRepository,
                VmIncidentWrapper.class,
                Incidents.class,
                "Incidents",
                appConfig.getSmIncidentUrl());
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadIncidensFromSm() {
        process();
    }
}
