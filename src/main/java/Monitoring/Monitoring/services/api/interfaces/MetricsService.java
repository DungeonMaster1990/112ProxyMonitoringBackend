package Monitoring.Monitoring.services.api.interfaces;

import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;

public interface MetricsService {
    VmMetricsResponse[] getMetrics(VmMetricsRequest vmMetricsRequest);
}
