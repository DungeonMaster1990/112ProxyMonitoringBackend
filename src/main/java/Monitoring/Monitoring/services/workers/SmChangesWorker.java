package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.db.repositories.interfaces.ChangesRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmChangeWrapper;
import Monitoring.Monitoring.mappers.wrappers.ChangeMapperWrapper;
import Monitoring.Monitoring.services.workers.BaseSmWorker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    public void loadIncidensFromSm() throws NotSupportedException {
        process();
    }
}
