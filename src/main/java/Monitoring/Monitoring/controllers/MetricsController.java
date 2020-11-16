package Monitoring.Monitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricInfoRequest;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricInfoResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmMetricsResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmUpdateResponse;
import Monitoring.Monitoring.services.api.interfaces.MetricsService;

@RestController
public class MetricsController {
    private MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping("/api/v1.0/metrics")
    public VmMetricsResponse[] get(@RequestBody VmMetricsRequest vmMetricsRequest) {
        return metricsService.getMetrics(vmMetricsRequest);
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
