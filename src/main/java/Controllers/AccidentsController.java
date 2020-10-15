package Controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmPlanRequest;
import dto.ViewModels.Request.VmPlanSectionRequest;
import dto.ViewModels.Response.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v{1.0}/accidents")
public class AccidentsController {

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public VmAccidentResponse[] get(@RequestBody VmPlanRequest vmAccidentsRequest)
    {
        return VmMock.vmAccidentResponse;
    }

    @GetMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public VmNewAccidentResponse getNewAccident()
    {
        return  VmMock.vmNewAccidentResponse;
    }

    @GetMapping(value = "/info", consumes = "application/json", produces = "application/json")
    public VmAccidentInfoResponse getAccidentInfo(@RequestParam String id)
    {
        return  VmMock.vmAccidentInfoResponse;
    }

    @GetMapping(value = "/workers", consumes = "application/json", produces = "application/json")
    public VmAccidentWorkersResponse getPlanWorkers(@RequestParam String id)
    {
        return  VmMock.vmAccidentWorkersResponse;
    }

    @GetMapping(value = "/history", consumes = "application/json", produces = "application/json")
    public VmAccidentHistoryResponse getPlanHistory(@RequestParam String id)
    {
        return VmMock.vmAccidentHistoryResponse;
    }

    @GetMapping(value = "/descriptions", consumes = "application/json", produces = "application/json")
    public VmAccidentDescriptionResponse[] getAccidentDescriptions(@RequestParam String id)
    {
        return VmMock.vmAccidentDescriptionResponse;
    }
}
