package ru.vtb.monitoring.vtb112.services.api.interfaces;

import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.dto.api.response.VmMetricsResponse;

import java.util.List;

public interface MetricsService {
    List<VmMetricsResponse> getMetrics(VmMetricsRequest vmMetricsRequest);

    List<VmMetricInfoResponse> getMetricsInfos(VmMetricInfoRequest vmMetricInfoRequest);
}
