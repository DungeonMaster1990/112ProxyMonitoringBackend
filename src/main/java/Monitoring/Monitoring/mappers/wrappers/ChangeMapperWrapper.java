package Monitoring.Monitoring.mappers.wrappers;

import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;
import org.springframework.stereotype.Component;

@Component
public class ChangeMapperWrapper  extends SmMapper <Changes, VmSmChange> {
}
