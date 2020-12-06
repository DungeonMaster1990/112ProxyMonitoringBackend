package ru.vtb.monitoring.vtb112.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.monitoring.vtb112.dto.api.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.services.api.interfaces.IncidentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static ru.vtb.monitoring.vtb112.utils.ValidationUtils.stringToInt;

@Slf4j
@RestController
@RequestMapping(value = PathConstants.ACCIDENTS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentsController {

    private final IncidentService incidentService;

    public AccidentsController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmAccidentResponse> getAccidents(@Valid @RequestBody VmAccidentsRequest request) {
        return incidentService.getAccidents(request);
    }

    @GetMapping("/new")
    public VmNewAccidentResponse getNewAccident() {
        return incidentService.getNewAccident();
    }

    @GetMapping("/info")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam @NotNull String id) {
        return incidentService.getAccidentInfo(stringToInt("id", id));
    }

    @GetMapping("/workers")
    public VmAccidentWorkersResponse getWorkers(@RequestParam @NotNull String id) {
        return incidentService.getWorkers(stringToInt("id", id));
    }

    @GetMapping("/descriptions")
    public List<VmAccidentDescriptionResponse> getAccidentDescriptions(@RequestParam @NotNull String id) {
        return incidentService.getAccidentDescriptions(stringToInt("id", id));
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public VmAccidentHistoryResponse getHistory(@RequestParam String id) {
        return null;
    }
}