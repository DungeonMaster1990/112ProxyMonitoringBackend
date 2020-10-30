package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.db.models.Accidents;
import Monitoring.Monitoring.db.repositories.interfaces.AccidentsRepository;
import Monitoring.Monitoring.dto.viewmodels.request.VmAccidentsRequest;
import Monitoring.Monitoring.dto.viewmodels.request.VmPlanRequest;
import Monitoring.Monitoring.dto.viewmodels.response.*;
import Monitoring.Monitoring.services.interfaces.AccidentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccidentsController {
    private AccidentsService accidentsService;

    @Autowired
    public AccidentsController(AccidentsService accidentsService) {
        this.accidentsService = accidentsService;
    }

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

//    @GetMapping("/api/v1.0/accidents/callDb")
//    public List<Accidents> getAllAccidents()
//    {
//        return accidentRepository.getAccident();
//    }
}
