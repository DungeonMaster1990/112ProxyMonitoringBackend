package Monitoring.Monitoring.services.api.interfaces;

import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricInfoRequest;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricInfoResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;

public interface MetricsService {
    VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequest);

    VmMetricInfoResponse[] getMetricsInfos(VmMetricInfoRequest vmMetricInfoRequest);
}
