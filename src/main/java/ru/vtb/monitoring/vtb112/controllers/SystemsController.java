package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmSystemResponse;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmUpdateResponse;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.SystemsService;

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
