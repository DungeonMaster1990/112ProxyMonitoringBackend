package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.viewmodels.request.VmSystemsRequest;
import Monitoring.Monitoring.dto.viewmodels.response.VmSystemResponse;
import Monitoring.Monitoring.dto.viewmodels.response.VmUpdateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemsController {

    @GetMapping(value = "/api/v1.0/systems", consumes = "application/json", produces = "application/json")
    public VmSystemResponse[] get(@RequestParam VmSystemsRequest vmSystemsRequest)
    {
        return VmMock.vmSystemResponse;
    }

    @GetMapping(value = "/api/v1.0/systems/affected", consumes = "application/json", produces = "application/json")
    public String[] getAffectedSystem()
    {
        return VmMock.affectedSystems;
    }

    @GetMapping(value = "/api/v1.0/systems/update", consumes = "application/json", produces = "application/json")
    public VmUpdateResponse updateSystems()
    {
        return VmMock.updateMetricsOrSystem;
    }
}
