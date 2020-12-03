package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.mappers.UnavailabilityMapper;

@ConditionalOnProperty(value = "sm.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SmUnavailabilityWorker extends BaseSmWorker<VmSmUnavailability, Unavailabilities> {

    SmUnavailabilityWorker(AppConfig appConfig,
                           UnavailabilitiesRepository unavailabilitiesRepository,
                           UnavailabilityMapper unavailabilityMapper,
                           UpdatesRepository updatesRepository) {
        super(appConfig.getSmPort(),
                unavailabilitiesRepository,
                unavailabilityMapper,
                updatesRepository,
                WorkerName.SM_UNAVAILABILITIES,
                appConfig.getSmUnavailabilityUrl());
    }

    @Scheduled(fixedRateString = "${sm.scheduler.fixedRate}")
    public void loadUnavailabilitiesFromSm() {
        process();
    }
}
