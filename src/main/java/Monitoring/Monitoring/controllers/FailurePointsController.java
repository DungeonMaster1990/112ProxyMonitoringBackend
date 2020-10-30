package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.services.interfaces.FailurePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailurePointsController {
    private FailurePointsService failurePointsService;

    @Autowired
    public FailurePointsController(FailurePointsService failurePointsService) {
        this.failurePointsService = failurePointsService;
    }

    @GetMapping("/api/v1.0/failure")
    public String[] get()
    {
        return VmMock.failurePoints;
    }
}
