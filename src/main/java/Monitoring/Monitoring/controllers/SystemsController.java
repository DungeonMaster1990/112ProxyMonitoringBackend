package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmSystemResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmUpdateResponse;
import Monitoring.Monitoring.services.api.interfaces.SystemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemsController {
    private SystemsService systemsService;

    @Autowired
    public SystemsController(SystemsService systemsService) {
        this.systemsService = systemsService;
    }

    @GetMapping("/api/v1.0/systems")
    public VmSystemResponse[] get(@RequestParam int page, @RequestParam int limit)
    {
        return VmMock.vmSystemResponse;
    }

    @GetMapping("/api/v1.0/systems/affected")
    public String[] getAffectedSystem()
    {
        return VmMock.affectedSystems;
    }

    @GetMapping("/api/v1.0/systems/update")
    public VmUpdateResponse updateSystems()
    {
        return VmMock.updateMetricsOrSystem;
    }
}
