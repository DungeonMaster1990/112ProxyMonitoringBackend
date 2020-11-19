package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MetricsMapper {
    @Mapping(source = "msname", target = "name")
    @Mapping(target = "mine", constant = "false")
    @Mapping(target = "delta", constant = "0L")
    @Mapping(target = "deltaPercent", constant = "0")
    @Mapping(target = "totalPercent", constant = "0")
    VmMetricsResponse mapToMetricsResponse(Metrics metrics);
}
