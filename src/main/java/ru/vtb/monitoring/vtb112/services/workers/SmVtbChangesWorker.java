package ru.vtb.monitoring.vtb112.services.workers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Changes;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.UpdatesRepository;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers.VmChangeWrapper;

@Component
public class SmVtbChangesWorker extends BaseSmWorker<VmSmChange, VmChangeWrapper, Changes> {
    @Autowired
    private SmVtbChangesWorker(
            AppConfig appConfig,
            ChangesRepository changesRepository,
            UpdatesRepository updatesRepository)
    {
        super(appConfig,
                changesRepository,
                new ModelMapper(),
                updatesRepository,
                VmChangeWrapper.class,
                Changes.class,
                "Changes",
                appConfig.getSmChangesUrl());
    }

    @Scheduled(fixedRateString = "${sm.methods.incident.fixedrate}")
    public void loadChangesFromSm() {
        process();
    }
}
