package ru.vtb.monitoring.vtb112.mappers.wrappers;

import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;

@Component
public class IncidentMapperWrapper extends SmMapper <Incident, VmSmIncident>{
}
