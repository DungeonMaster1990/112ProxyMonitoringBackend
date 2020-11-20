package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Incident;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmIncidentWrapper;
import Monitoring.Monitoring.services.workers.BaseSmWorker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SmIncidentWorker extends BaseSmWorker<VmSmIncident, VmIncidentWrapper, Incident> {

    @Autowired
    private SmIncidentWorker(
            AppConfig appConfig,
            IncidentRepository incidentRepository,
            UpdatesRepository updatesRepository)
    {
        super(appConfig,
                incidentRepository,
                new ModelMapper(),
                updatesRepository,
                VmIncidentWrapper.class,
                Incident.class,
                "Incidents",
                appConfig.getSmIncidentUrl());
    }

    //@Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadIncidentsFromSm() {
        process();
    }
}
