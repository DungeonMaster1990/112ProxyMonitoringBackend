package ru.vtb.monitoring.vtb112.mappers.wrappers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.db.models.Changes;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;
import ru.vtb.monitoring.vtb112.mappers.ChangesMapper;
import ru.vtb.monitoring.vtb112.mappers.IncidentMapper;
import ru.vtb.monitoring.vtb112.mappers.UnavailabilityMapper;

import javax.transaction.NotSupportedException;

@Getter
@Setter
@Service
public class SmMapper <T, M> {
    private IncidentMapper incidentMapper;
    private UnavailabilityMapper unavailabilityMapper;
    private ChangesMapper changesMapper;

    public T map(M model, Class<T> dbType, Class<M> vmType) throws NotSupportedException {
        if (dbType == Incident.class && vmType == VmSmIncident.class)
            return (T)incidentMapper.mapToIncidentResponse((VmSmIncident)model);
        else if (dbType == Unavailabilities.class && vmType == VmSmUnavailability.class)
            return (T)unavailabilityMapper.mapToIncidentResponse((VmSmUnavailability)model);
        else if (dbType == Changes.class && vmType == VmSmChange.class)
            return  (T)changesMapper.mapToChangesResponse((VmSmChange)model);

        throw new NotSupportedException();
    }
}
