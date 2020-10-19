package Monitoring.Monitoring.controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmMetricInfoRequest;
import dto.ViewModels.Request.VmMetricsRequest;
import dto.ViewModels.Response.VmMetricInfoResponse;
import dto.ViewModels.Response.VmMetricsResponse;
import dto.ViewModels.Response.VmUpdateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
