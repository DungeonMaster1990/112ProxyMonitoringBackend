package ru.vtb.monitoring.vtb112.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.*;
import ru.vtb.monitoring.vtb112.services.api.interfaces.IncidentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = PathConstants.ACCIDENTS, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AccidentsController {

    private final IncidentService incidentService;

    public AccidentsController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public List<VmAccidentResponse> getAccidents(@RequestBody VmAccidentsRequest request) {
        return incidentService.getAccidents(request);
    }

    @GetMapping("/new")
    public VmNewAccidentResponse getNewAccident() {
        return incidentService.getNewAccident();
    }

    @GetMapping("/info")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam Integer id) {
        return incidentService.getAccidentInfo(id);
    }

    @GetMapping("/workers")
    public VmAccidentWorkersResponse getWorkers(@RequestParam Integer id) {
        return incidentService.getWorkers(id);
    }

    @GetMapping("/descriptions")
    public List<VmAccidentDescriptionResponse> getAccidentDescriptions(@RequestParam Integer id) {
        return incidentService.getAccidentDescriptions(id);
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public VmAccidentHistoryResponse getHistory(@RequestParam String id) {
        return null;
    }

}