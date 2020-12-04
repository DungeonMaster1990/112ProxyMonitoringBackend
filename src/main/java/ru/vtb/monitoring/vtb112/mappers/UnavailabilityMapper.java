package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface UnavailabilityMapper extends ResponseMapper<Unavailabilities, VmSmUnavailability> {

    @Mapping(target = "id", ignore = true)
    @Override
    Unavailabilities mapToResponse(VmSmUnavailability source);

    @Mapping(target = "id", ignore = true)
    void updateUnavailability(Unavailabilities incident, @MappingTarget Unavailabilities updated);

    default Duration mapStringToDuration(String value) {
        if (value == null || value.isBlank()) return null;
        return Duration.parse(value);
    }
}
