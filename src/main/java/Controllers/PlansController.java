package Controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmPlanRequest;
import dto.ViewModels.Request.VmPlanSectionRequest;
import dto.ViewModels.Response.*;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v{1.0}/plans")
public class PlansController {

    @PostMapping(value = "/sections", consumes = "application/json", produces = "application/json")
    public VmPlanSectionsResponse[] getPlanSections(@RequestBody VmPlanSectionRequest vmPlanSectionRequest)
    {
        return VmMock.vmPlanSectionsResponse;
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public VmPlanResponse[] get(@RequestBody VmPlanRequest vmPlanRequest)
    {
        return VmMock.vmPlanResponse;
    }

    @GetMapping(value = "/info", consumes = "application/json", produces = "application/json")
    public VmPlanResponse[] getPlanInfo(@RequestParam String id)
    {
        return  VmMock.vmPlanResponse;
    }

    @GetMapping(value = "/workers", consumes = "application/json", produces = "application/json")
    public VmPlanWorkers getPlanWorkers(@RequestParam String id)
    {
        return  VmMock.vmPlanWorkers;
    }

    @GetMapping(value = "/history", consumes = "application/json", produces = "application/json")
    public VmPlanHistoryResponse getPlanHistory(@RequestParam String id)
    {
        return VmMock.vmPlanHistoryResponse;
    }

    @GetMapping(value = "/descriptions", consumes = "application/json", produces = "application/json")
    public VmPlanDescriptionResponse[] AccidentDescriptions(@RequestParam String id)
    {
        return VmMock.vmPlanDescriptionResponse;
    }
}
