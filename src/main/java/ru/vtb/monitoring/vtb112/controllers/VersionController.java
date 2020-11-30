package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmVersion;

@RestController
@RequestMapping(value = PathConstants.API_VERSION, produces = MediaType.APPLICATION_JSON_VALUE)
public class VersionController {

    @GetMapping(value = "/checkVersion")
    public VmVersion getVersion(@RequestParam String version) {
        return new VmVersion("normal");
    }
}
