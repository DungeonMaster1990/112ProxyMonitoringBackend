package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPlanRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPlanSectionRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.*;
import ru.vtb.monitoring.vtb112.services.api.interfaces.PlansService;

import java.util.List;

@RestController
@RequestMapping(value = PathConstants.PLANS, produces = MediaType.APPLICATION_JSON_VALUE)
public class PlansController {

    private final PlansService plansService;

    public PlansController(PlansService plansService) {
        this.plansService = plansService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmPlanResponse> get(@RequestBody VmPlanRequest request) {
        return plansService.getSection(
                request.getPlanSectionID().getSection(),
                request.getKeyword(),
                PageRequest.of(request.getPage() - 1, request.getLimit()));
    }

    @PostMapping(value = "/sections", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmPlanSectionsResponse> getPlanSections(@RequestBody VmPlanSectionRequest request) {
        return plansService.getSections(
                request.getStartDate(),
                request.getFinishDate());
    }

    @GetMapping("/info")
    public VmPlanInfoResponse getPlanInfo(@RequestParam Integer id) {
        return plansService.getInfo(id);
    }

    @GetMapping("/workers")
    public VmPlanWorkersResponse getPlanWorkers(@RequestParam Integer id) {
        return plansService.getWorkers(id);
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public VmPlanHistoryResponse getPlanHistory(@RequestParam Integer id) {
        return null;
    }

    @GetMapping("/descriptions")
    public List<VmPlanDescriptionResponse> getPlanDescriptions(@RequestParam Integer id) {
        return plansService.getDescriptions(id);
    }
}
