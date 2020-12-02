package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vtb.monitoring.vtb112.db.pg.models.Metrics;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricsResponse;

@Mapper(componentModel = "spring")
public interface MetricsMapper {

    @Mapping(target = "deltaStatus", ignore = true)
    @Mapping(target = "value", ignore = true)
    @Mapping(source = "msname", target = "name")
    @Mapping(target = "mine", constant = "false")
    @Mapping(target = "delta", constant = "0L")
    @Mapping(target = "deltaPercent", constant = "0")
    @Mapping(target = "totalPercent", constant = "0")
    VmMetricsResponse mapToMetricsResponse(Metrics metrics);
}
