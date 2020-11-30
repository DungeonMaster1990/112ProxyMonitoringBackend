package ru.vtb.monitoring.vtb112.mappers.wrappers;

import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.db.models.Changes;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;

@Component
public class ChangeMapperWrapper  extends SmMapper <Changes, VmSmChange> {
}
