package ru.vtb.monitoring.vtb112.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.*;
import ru.vtb.monitoring.vtb112.services.api.interfaces.IncidentService;

import java.util.List;

import static ru.vtb.monitoring.vtb112.services.helpers.ValidationUtils.stringToInt;
import static ru.vtb.monitoring.vtb112.services.helpers.ValidationUtils.validatePageAndLimit;

@Slf4j
@RestController
@RequestMapping(value = PathConstants.ACCIDENTS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentsController {

    private final IncidentService incidentService;

    public AccidentsController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmAccidentResponse> getAccidents(@RequestBody VmAccidentsRequest request) {
        validatePageAndLimit(request);
        return incidentService.getAccidents(request);
    }

    @GetMapping("/new")
    public VmNewAccidentResponse getNewAccident() {
        return incidentService.getNewAccident();
    }

    @GetMapping("/info")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam String id) {
        return incidentService.getAccidentInfo(stringToInt("id", id));
    }

    @GetMapping("/workers")
    public VmAccidentWorkersResponse getWorkers(@RequestParam String id) {
        return incidentService.getWorkers(stringToInt("id", id));
    }

    @GetMapping("/descriptions")
    public List<VmAccidentDescriptionResponse> getAccidentDescriptions(@RequestParam String id) {
        return incidentService.getAccidentDescriptions(stringToInt("id", id));
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public VmAccidentHistoryResponse getHistory(@RequestParam String id) {
        return null;
    }
}