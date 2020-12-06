package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.response.VmSystemResponse;
import ru.vtb.monitoring.vtb112.dto.api.response.VmUpdateResponse;
import ru.vtb.monitoring.vtb112.services.api.interfaces.SystemsService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = PathConstants.SYSTEMS, produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemsController {

    private final SystemsService systemsService;

    public SystemsController(SystemsService systemsService) {
        this.systemsService = systemsService;
    }

    @GetMapping
    public List<VmSystemResponse> get(@RequestParam @Min(1) int page, @RequestParam @Min(1) int limit) {
        return systemsService.get(page, limit);
    }

    @GetMapping("/affected")
    public List<String> getAffectedServices() {
        return systemsService.getTop10Unavailabilities();
    }

    @GetMapping("/update")
    public VmUpdateResponse updateSystems() {
        return new VmUpdateResponse(true);
    }
}
