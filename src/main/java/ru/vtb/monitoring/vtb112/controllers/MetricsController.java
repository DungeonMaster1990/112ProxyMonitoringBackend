package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricInfoRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmMetricsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricInfoResponse;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmMetricsResponse;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmUpdateResponse;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.MetricsService;

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
    public VmMetricInfoResponse[] getMetricInfo(@RequestBody VmMetricInfoRequest vmMetricInfoRequest) {
        return metricsService.getMetricsInfos(vmMetricInfoRequest);
    }
}
