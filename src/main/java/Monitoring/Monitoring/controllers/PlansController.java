package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.viewmodels.request.VmPlanRequest;
import Monitoring.Monitoring.dto.viewmodels.request.VmPlanSectionRequest;
import Monitoring.Monitoring.dto.viewmodels.response.*;
import Monitoring.Monitoring.services.interfaces.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlansController {
    private PlansService plansService;

    @Autowired
    public PlansController(PlansService plansService) {
        this.plansService = plansService;
    }

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
    public VmPlanInfoResponse getPlanInfo(@RequestParam String id)
    {
        return  VmMock.vmPlanInfoResponse;
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
