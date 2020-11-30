package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmUnavailabilityWrapper;

@ConditionalOnProperty(value = "sm.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SmUnavailabilityWorker extends BaseSmWorker<VmSmUnavailability, VmUnavailabilityWrapper, Unavailabilities> {
    @Autowired
    private SmUnavailabilityWorker(
            AppConfig appConfig,
            UnavailabilitiesRepository unavailabilitiesRepository,
            UpdatesRepository updatesRepository) {
        super(appConfig,
                unavailabilitiesRepository,
                updatesRepository,
                VmUnavailabilityWrapper.class,
                Unavailabilities.class,
                "Unavailabilities",
                appConfig.getSmUnavailabilityUrl());
    }

    @Scheduled(fixedRateString = "${sm.scheduler.fixedRate}")
    public void loadUnavailabilitiesFromSm() {
        process();
    }
}
