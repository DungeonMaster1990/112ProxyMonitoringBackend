package Controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmMetricsRequest;
import dto.ViewModels.Response.VmMetricsResponse;
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
}
