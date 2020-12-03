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

import static ru.vtb.monitoring.vtb112.services.helpers.ValidationUtils.*;

@RestController
@RequestMapping(value = PathConstants.PLANS, produces = MediaType.APPLICATION_JSON_VALUE)
public class PlansController {

    private final PlansService plansService;

    public PlansController(PlansService plansService) {
        this.plansService = plansService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VmPlanResponse> get(@RequestBody VmPlanRequest request) {
        validatePlansRequest(request);
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
    public VmPlanInfoResponse getPlanInfo(@RequestParam String id) {
        return plansService.getInfo(stringToInt("id", id));
    }

    @GetMapping("/workers")
    public VmPlanWorkersResponse getPlanWorkers(@RequestParam String id) {
        return plansService.getWorkers(stringToInt("id", id));
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public VmPlanHistoryResponse getPlanHistory(@RequestParam String id) {
        return null;
    }

    @GetMapping("/descriptions")
    public List<VmPlanDescriptionResponse> getPlanDescriptions(@RequestParam String id) {
        return plansService.getDescriptions(stringToInt("id", id));
    }
}
