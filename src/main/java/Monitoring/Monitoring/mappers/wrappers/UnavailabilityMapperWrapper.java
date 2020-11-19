package Monitoring.Monitoring.mappers.wrappers;

import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import org.springframework.stereotype.Component;

@Component
public class UnavailabilityMapperWrapper extends SmMapper <Unavailabilities, VmSmUnavailability> {
}
