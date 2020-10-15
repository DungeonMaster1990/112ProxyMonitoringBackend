package Controllers;

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
@RequestMapping("/api/v{1.0}/metrics")
public class MetricsController {

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public VmMetricsResponse[] get(@RequestBody VmMetricsRequest vmMetricsRequest)
    {
        return VmMock.vmMetricsResponse;
    }

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public VmUpdateResponse updateMetrics(@RequestBody String[] vmUpdateRequest)
    {
        return VmMock.updateMetricsOrSystem;
    }

    @PostMapping(value = "/info", consumes = "application/json", produces = "application/json")
    public VmMetricInfoResponse[] getMetricInfo(@RequestBody VmMetricInfoRequest vmMetricInfoRequest)
    {
        return VmMock.vmMetricInfoResponse;
    }
}
