package ru.vtb.monitoring.vtb112.services.workers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmIncidentWrapper;

@Component
public class SmIncidentWorker extends BaseSmWorker<VmSmIncident, VmIncidentWrapper, Incident> {

    @Autowired
    private SmIncidentWorker(
            AppConfig appConfig,
            IncidentRepository incidentRepository,
            UpdatesRepository updatesRepository,
            HttpComponentsClientHttpRequestFactory httpRequestFactory)
    {
        super(appConfig,
                incidentRepository,
                new ModelMapper(),
                updatesRepository,
                VmIncidentWrapper.class,
                Incident.class,
                "Incidents",
                appConfig.getSmIncidentUrl(),
                httpRequestFactory);
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadIncidentsFromSm() {
        process();
    }
}
