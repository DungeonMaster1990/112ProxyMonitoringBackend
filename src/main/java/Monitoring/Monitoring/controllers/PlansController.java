package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.dto.api.viewmodels.request.VmPlanRequest;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmPlanSectionRequest;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanDescriptionResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanHistoryResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanInfoResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanSectionsResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanWorkersResponse;
import Monitoring.Monitoring.services.api.interfaces.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1.0/plans", produces = "application/json;charset=UTF-8")
public class PlansController {

    @Autowired
    private PlansService plansService;

    @PostMapping(value = "", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<List<VmPlanResponse>> get(@RequestBody VmPlanRequest request) {
        return new ResponseEntity<>(plansService.getSection(
                request.getPlanSectionID().getSection(),
                request.getKeyword(),
                PageRequest.of(request.getPage()-1, request.getLimit())),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/sections", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<List<VmPlanSectionsResponse>> getPlanSections(@RequestBody VmPlanSectionRequest request) {
        return new ResponseEntity<>(plansService.getSections(
                request.getStartDate(),
                request.getFinishDate()),
                HttpStatus.OK
        );
    }

    @GetMapping("/info")
    public ResponseEntity<VmPlanInfoResponse> getPlanInfo(@RequestParam Integer id) {
        return new ResponseEntity<>(plansService.getInfo(id), HttpStatus.OK);
    }

    @GetMapping("/workers")
    public ResponseEntity<VmPlanWorkersResponse> getPlanWorkers(@RequestParam Integer id) {
        return new ResponseEntity<>(plansService.getWorkers(id), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<VmPlanHistoryResponse> getPlanHistory(@RequestParam Integer id) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/descriptions")
    public ResponseEntity<List<VmPlanDescriptionResponse>> getPlanDescriptions(@RequestParam Integer id) {
        return new ResponseEntity<>(plansService.getDescriptions(id), HttpStatus.OK);
    }
}
