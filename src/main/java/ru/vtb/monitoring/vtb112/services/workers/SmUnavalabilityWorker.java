package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmUnavailabilityWrapper;
import ru.vtb.monitoring.vtb112.mappers.wrappers.UnavailabilityMapperWrapper;

import javax.transaction.NotSupportedException;

@Component
public class SmUnavalabilityWorker extends BaseSmWorker<VmSmUnavailability, VmUnavailabilityWrapper, Unavailabilities> {
    @Autowired
    private SmUnavalabilityWorker(
            AppConfig appConfig,
            UnavailabilitiesRepository unavailabilitiesRepository,
            UnavailabilityMapperWrapper unavailabilityMapperWrapper,
            UpdatesRepository updatesRepository)
    {
        super(appConfig,
                unavailabilitiesRepository,
                unavailabilityMapperWrapper,
                updatesRepository,
                VmSmUnavailability.class,
                VmUnavailabilityWrapper.class,
                Unavailabilities.class,
                "Unavalabilities",
                appConfig.getSmUnavailabilityUrl());
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadUnavailabilitiesFromSm() throws NotSupportedException {
        process();
    }
}
