package ru.vtb.monitoring.vtb112.services.api.interfaces;

import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricsResponse;

public interface MetricsService {
    VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequest);

    VmMetricInfoResponse[] getMetricsInfos(VmMetricInfoRequest vmMetricInfoRequest);
}
