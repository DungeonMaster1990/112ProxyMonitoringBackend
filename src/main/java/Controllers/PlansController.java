package Controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmPlanRequest;
import dto.ViewModels.Request.VmPlanSectionRequest;
import dto.ViewModels.Response.VmPlanInfoResponse;
import dto.ViewModels.Response.VmPlanResponse;
import dto.ViewModels.Response.VmPlanSectionsResponse;
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

    @PostMapping("")
    public VmPlanResponse[] get(@RequestBody VmPlanRequest vmPlanRequest)
    {
        return VmMock.vmPlanResponse;
    }

    @GetMapping("/info/{id}")
    public VmPlanResponse[] getPlanInfo(@PathVariable String id)
    {
        return  VmMock.vmPlanResponse;
    }
}
