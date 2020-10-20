package Monitoring.Monitoring.controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmPlanRequest;
import dto.ViewModels.Response.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccidentsController {

    @PostMapping(value = "/api/v1/accidents", consumes = "application/json", produces = "application/json")
    public VmAccidentResponse[] get(@RequestBody VmPlanRequest vmAccidentsRequest)
    {
        return VmMock.vmAccidentResponse;
    }

    @GetMapping(value = "/api/v1/accidents/new", consumes = "application/json", produces = "application/json")
    public VmNewAccidentResponse getNewAccident()
    {
        return  VmMock.vmNewAccidentResponse;
    }

    @GetMapping(value = "/api/v1/accidents/info", consumes = "application/json", produces = "application/json")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam String id)
    {
        return  VmMock.vmAccidentInfoResponse;
    }

    @GetMapping(value = "/api/v1/accidents/workers", consumes = "application/json", produces = "application/json")
    public VmAccidentWorkersResponse getWorkers(@RequestParam String id)
    {
        return  VmMock.vmAccidentWorkersResponse;
    }

    @GetMapping(value = "/api/v1/accidents/history", consumes = "application/json", produces = "application/json")
    public VmAccidentHistoryResponse getPlanHistory(@RequestParam String id)
    {
        return VmMock.vmAccidentHistoryResponse;
    }

    @GetMapping(value = "/api/v1/accidents/descriptions", consumes = "application/json", produces = "application/json")
    public VmAccidentDescriptionResponse[] getAccidentDescriptions(@RequestParam String id)
    {
        return VmMock.vmAccidentDescriptionResponse;
    }
}
