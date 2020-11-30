package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    @Mapping(target = "incidentId", source = "source.id")
    @Mapping(target = "affectedSystems", ignore = true)
    @Mapping(target = "notificationSent", ignore = true)
    @Mapping(target = "statusType", ignore = true)
    @Mapping(target = "id", ignore = true)
    Incident mapToIncidentResponse(VmSmIncident source);

    @Mapping(target = "id", ignore = true)
    void updateIncident(Incident incident, @MappingTarget Incident updated);

    default String mapDescription(String[] value) {
        return value != null
                ? String.join(System.lineSeparator(), Arrays.asList(value))
                : null;
    }
}
