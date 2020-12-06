package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.enums.VersionStatus;
import ru.vtb.monitoring.vtb112.dto.api.response.VmVersion;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = PathConstants.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class VersionController {

    @GetMapping(value = "/checkVersion")
    public VmVersion getVersion(@RequestParam @NotNull String version) {
        VersionStatus status = PathConstants.VERSION.equals(version) ? VersionStatus.NORMAL : VersionStatus.UNSUPPORTED;
        return new VmVersion(status);
    }
}
