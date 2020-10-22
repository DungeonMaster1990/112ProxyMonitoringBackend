package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.viewmodels.request.VmPlanRequest;
import Monitoring.Monitoring.dto.viewmodels.response.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccidentsController {

    @PostMapping(value = "/api/v1.0/accidents", consumes = "application/json", produces = "application/json")
    public VmAccidentResponse[] get(@RequestBody VmPlanRequest vmAccidentsRequest)
    {
        return VmMock.vmAccidentResponse;
    }

    @GetMapping(value = "/api/v1.0/accidents/new", consumes = "application/json", produces = "application/json")
    public VmNewAccidentResponse getNewAccident()
    {
        return  VmMock.vmNewAccidentResponse;
    }

    @GetMapping(value = "/api/v1.0/accidents/info", consumes = "application/json", produces = "application/json")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam String id)
    {
        return  VmMock.vmAccidentInfoResponse;
    }

    @GetMapping(value = "/api/v1.0/accidents/workers", consumes = "application/json", produces = "application/json")
    public VmAccidentWorkersResponse getWorkers(@RequestParam String id)
    {
        return  VmMock.vmAccidentWorkersResponse;
    }

    @GetMapping(value = "/api/v1.0/accidents/history", consumes = "application/json", produces = "application/json")
    public VmAccidentHistoryResponse getHistory(@RequestParam String id)
    {
        return VmMock.vmAccidentHistoryResponse;
    }

    @GetMapping(value = "/api/v1.0/accidents/descriptions", consumes = "application/json", produces = "application/json")
    public VmAccidentDescriptionResponse[] getAccidentDescriptions(@RequestParam String id)
    {
        return VmMock.vmAccidentDescriptionResponse;
    }
}
