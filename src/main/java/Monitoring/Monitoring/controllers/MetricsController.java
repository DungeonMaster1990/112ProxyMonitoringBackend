package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.viewmodels.request.VmMetricInfoRequest;
import Monitoring.Monitoring.dto.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.viewmodels.response.VmMetricInfoResponse;
import Monitoring.Monitoring.dto.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.dto.viewmodels.response.VmUpdateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    @PostMapping("/api/v1.0/metrics")
    public VmMetricsResponse[] get(@RequestBody VmMetricsRequest vmMetricsRequest)
    {
        return VmMock.vmMetricsResponse;
    }

    @PostMapping("/api/v1.0/metrics/update")
    public VmUpdateResponse updateMetrics(@RequestBody String[] vmUpdateRequest)
    {
        return VmMock.updateMetricsOrSystem;
    }

    @PostMapping("/api/v1.0/metrics/info")
    public VmMetricInfoResponse[] getMetricInfo(@RequestBody VmMetricInfoRequest vmMetricInfoRequest)
    {
        return VmMock.vmMetricInfoResponse;
    }
}
