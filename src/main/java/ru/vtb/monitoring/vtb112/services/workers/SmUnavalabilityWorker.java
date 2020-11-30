package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.db.repositories.interfaces.UnavailabilitiesRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmUnavailabilityWrapper;
import Monitoring.Monitoring.mappers.UnavailabilityMapper;
import Monitoring.Monitoring.mappers.wrappers.ChangeMapperWrapper;
import Monitoring.Monitoring.mappers.wrappers.UnavailabilityMapperWrapper;
import Monitoring.Monitoring.services.workers.BaseSmWorker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
