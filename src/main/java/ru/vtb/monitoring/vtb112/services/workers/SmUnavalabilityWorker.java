package ru.vtb.monitoring.vtb112.services.workers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmUnavailabilityWrapper;

@Component
public class SmUnavalabilityWorker extends BaseSmWorker<VmSmUnavailability, VmUnavailabilityWrapper, Unavailabilities> {
    @Autowired
    private SmUnavalabilityWorker(
            AppConfig appConfig,
            UnavailabilitiesRepository unavailabilitiesRepository,
            UpdatesRepository updatesRepository,
            HttpComponentsClientHttpRequestFactory httpRequestFactory)
    {
        super(appConfig,
                unavailabilitiesRepository,
                new ModelMapper(),
                updatesRepository,
                VmUnavailabilityWrapper.class,
                Unavailabilities.class,
                "Unavalabilities",
                appConfig.getSmUnavailabilityUrl(),
                httpRequestFactory);
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadUnavailabilitiesFromSm() {
        process();
    }
}
