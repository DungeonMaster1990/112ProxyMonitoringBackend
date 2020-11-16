package Monitoring.Monitoring.mappers;

import org.mapstruct.Mapper;

import Monitoring.Monitoring.db.models.Metrics;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;

@Mapper(componentModel = "spring")
public interface MetricsMapper {
    VmMetricsResponse mapToMetricsResponse(Metrics metrics);
}
