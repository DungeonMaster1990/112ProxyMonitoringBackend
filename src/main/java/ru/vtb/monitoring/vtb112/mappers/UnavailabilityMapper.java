package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;

@Mapper(componentModel = "spring")
public interface UnavailabilityMapper {

    @Mapping(target = "id", ignore = true)
    Unavailabilities mapToIncidentResponse(VmSmUnavailability source);

    @Mapping(target = "id", ignore = true)
    void updateUnavailability(Unavailabilities incident, @MappingTarget Unavailabilities updated);
}
