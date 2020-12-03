package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;
import ru.vtb.monitoring.vtb112.mappers.ChangesMapper;

@ConditionalOnProperty(value = "sm.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SmChangesWorker extends BaseSmWorker<VmSmChange, Changes> {

    SmChangesWorker(AppConfig appConfig,
                    ChangesRepository changesRepository,
                    ChangesMapper changesMapper,
                    UpdatesRepository updatesRepository) {
        super(appConfig.getSmPort(),
                changesRepository,
                changesMapper,
                updatesRepository,
                WorkerName.SM_CHANGES,
                appConfig.getSmChangesUrl());
    }

    @Scheduled(fixedRateString = "${sm.scheduler.fixedRate}")
    public void loadChangesFromSm() {
        process();
    }
}
