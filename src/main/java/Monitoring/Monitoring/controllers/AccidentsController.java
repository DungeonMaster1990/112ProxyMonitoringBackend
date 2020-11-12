package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmAccidentsRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccidentsController {

    @PostMapping("/api/v1.0/accidents")
    public VmAccidentResponse[] get(@RequestBody VmAccidentsRequest vmAccidentsRequest)
    {
        return VmMock.vmAccidentResponse;
    }

    @GetMapping("/api/v1.0/accidents/new")
    public VmNewAccidentResponse getNewAccident()
    {
        return  VmMock.vmNewAccidentResponse;
    }

    @GetMapping("/api/v1.0/accidents/info")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam String id)
    {
        return  VmMock.vmAccidentInfoResponse;
    }

    @GetMapping("/api/v1.0/accidents/workers")
    public VmAccidentWorkersResponse getWorkers(@RequestParam String id)
    {
        return  VmMock.vmAccidentWorkersResponse;
    }

    @GetMapping("/api/v1.0/accidents/history")
    public VmAccidentHistoryResponse getHistory(@RequestParam String id)
    {
        return VmMock.vmAccidentHistoryResponse;
    }

    @GetMapping("/api/v1.0/accidents/descriptions")
    public VmAccidentDescriptionResponse[] getAccidentDescriptions(@RequestParam String id)
    {
        return VmMock.vmAccidentDescriptionResponse;
    }
}
