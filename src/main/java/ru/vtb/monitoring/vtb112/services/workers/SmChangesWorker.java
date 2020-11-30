package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Changes;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmChangeWrapper;
import ru.vtb.monitoring.vtb112.mappers.wrappers.ChangeMapperWrapper;

import javax.transaction.NotSupportedException;

@Component
public class SmChangesWorker extends BaseSmWorker<VmSmChange, VmChangeWrapper, Changes> {
    @Autowired
    private SmChangesWorker(
            AppConfig appConfig,
            ChangesRepository changesRepository,
            ChangeMapperWrapper changeMapperWrapper,
            UpdatesRepository updatesRepository)
    {
        super(appConfig,
                changesRepository,
                changeMapperWrapper,
                updatesRepository,
                VmSmChange.class,
                VmChangeWrapper.class,
                Changes.class,
                "Changes",
                appConfig.getSmChangesUrl());
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadChangesFromSm() throws NotSupportedException {
        process();
    }
}
