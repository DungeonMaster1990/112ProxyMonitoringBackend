package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmSystemResponse;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmUpdateResponse;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.SystemsService;

@RestController
@RequestMapping(value = PathConstants.SYSTEMS, produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemsController {

    private final SystemsService systemsService;

    public SystemsController(SystemsService systemsService) {
        this.systemsService = systemsService;
    }

    @GetMapping
    public VmSystemResponse[] get(@RequestParam int page, @RequestParam int limit) {
        return VmMock.vmSystemResponse;
    }

    @GetMapping("/affected")
    public String[] getAffectedSystem() {
        return VmMock.affectedSystems;
    }

    @GetMapping("/update")
    public VmUpdateResponse updateSystems() {
        return VmMock.updateMetricsOrSystem;
    }
}
