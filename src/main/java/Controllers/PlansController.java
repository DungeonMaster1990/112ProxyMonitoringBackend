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

    @PostMapping("/")
    public VmPlanResponse[] get(@RequestBody VmPlanRequest vmPlanRequest)
    {
        return VmMock.vmPlanResponse;
    }

    @GetMapping("/info/{id}")
    public VmPlanResponse[] getPlanInfo(@PathVariable String id)
    {
        return  VmMock.vmPlanResponse;
    }

    @GetMapping("/workers")
    public VmPlanWorkers getPlanWorkers(@PathVariable String id)
    {
        return  VmMock.vmPlanWorkers;
    }

    @GetMapping("/history")
    public VmPlanHistoryResponse getPlanHistory(@PathVariable String id)
    {
        return VmMock.vmPlanHistoryResponse;
    }

    @GetMapping("/descriptions")
    public VmPlanDescriptionResponse[] AccidentDescriptions(@PathVariable String id)
    {
        return VmMock.vmPlanDescriptionResponse;
    }
}
