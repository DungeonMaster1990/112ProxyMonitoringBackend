package Monitoring.Monitoring.mappers.wrappers;

import Monitoring.Monitoring.db.models.Incident;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import org.springframework.stereotype.Component;

@Component
public class IncidentMapperWrapper extends SmMapper <Incident, VmSmIncident>{
}
