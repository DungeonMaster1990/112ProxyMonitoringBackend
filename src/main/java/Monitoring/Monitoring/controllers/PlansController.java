package Monitoring.Monitoring.controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmPlanRequest;
import dto.ViewModels.Request.VmPlanSectionRequest;
import dto.ViewModels.Response.*;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlansController {

    @PostMapping("/api/v1.0/plans/sections")
    public VmPlanSectionsResponse[] getPlanSections(@RequestBody VmPlanSectionRequest vmPlanSectionRequest)
    {
        return VmMock.vmPlanSectionsResponse;
    }

    @PostMapping("/api/v1.0/plans")
    public VmPlanResponse[] get(@RequestBody VmPlanRequest vmPlanRequest)
    {
        return VmMock.vmPlanResponse;
    }

    @GetMapping("/api/v1.0/plans/info")
    public VmPlanResponse[] getPlanInfo(@RequestParam String id)
    {
        return  VmMock.vmPlanResponse;
    }

    @GetMapping("/api/v1.0/plans/workers")
    public VmPlanWorkersResponse getPlanWorkers(@RequestParam String id)
    {
        return  VmMock.vmPlanWorkersResponse;
    }

    @GetMapping("/api/v1.0/plans/history")
    public VmPlanHistoryResponse getPlanHistory(@RequestParam String id)
    {
        return VmMock.vmPlanHistoryResponse;
    }

    @GetMapping("/api/v1.0/plans/descriptions")
    public VmPlanDescriptionResponse[] getPlanDescriptions(@RequestParam String id)
    {
        return VmMock.vmPlanDescriptionResponse;
    }
}
