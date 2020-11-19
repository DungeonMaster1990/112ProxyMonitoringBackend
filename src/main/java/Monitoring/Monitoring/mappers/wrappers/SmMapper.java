package Monitoring.Monitoring.mappers.wrappers;

import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.db.models.Incident;
import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import Monitoring.Monitoring.mappers.IncidentMapper;
import Monitoring.Monitoring.mappers.UnavailabilityMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;

@Getter
@Setter
@Service
public class SmMapper <T, M> {
    private IncidentMapper incidentMapper;
    private UnavailabilityMapper unavailabilityMapper;

    public T map(M model, Class<T> dbType, Class<M> vmType) throws NotSupportedException {
        if (dbType == Incident.class && vmType == VmSmIncident.class)
            return (T)incidentMapper.mapToIncidentResponse((VmSmIncident)model);
        else if (dbType == Unavailabilities.class && vmType == VmSmUnavailability.class)
            return (T)unavailabilityMapper.mapToUnavailabilityResponse((VmSmUnavailability)model);
        else if (dbType == Changes.class && vmType == VmSmChange.class)
        {}

        throw new NotSupportedException();
    }
}
