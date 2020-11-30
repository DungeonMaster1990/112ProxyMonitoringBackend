package ru.vtb.monitoring.vtb112.mappers.wrappers;

import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;

@Component
public class UnavailabilityMapperWrapper extends SmMapper<Unavailabilities, VmSmUnavailability> {
}
