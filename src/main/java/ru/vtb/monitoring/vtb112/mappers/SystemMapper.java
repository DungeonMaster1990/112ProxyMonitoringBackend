package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedIncidents;
import ru.vtb.monitoring.vtb112.dto.api.response.VmSystemResponse;

@Mapper(componentModel = "spring")
public interface SystemMapper {

    @Mapping(target = "id", constant = "1")
    @Mapping(target = "name", constant = "Все аварии")
    @Mapping(target = "mine", constant = "true")
    @Mapping(target = "criticalAccidents", source = "firstCategory")
    @Mapping(target = "majorAccidents", source = "secondCategory")
    @Mapping(target = "minorAccidents", source = "thirdCategory")
    VmSystemResponse mapToResponse(GroupedIncidents groupedIncidents);
}
