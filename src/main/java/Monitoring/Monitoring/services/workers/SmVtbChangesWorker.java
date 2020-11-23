package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.db.repositories.interfaces.ChangesRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmChangeWrapper;
import Monitoring.Monitoring.services.workers.BaseSmWorker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    public void loadIncidensFromSm() {
        process();
    }
}
