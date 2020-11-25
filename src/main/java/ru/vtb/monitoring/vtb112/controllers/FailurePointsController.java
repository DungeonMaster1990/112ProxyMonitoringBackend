package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.FailurePointsService;

@RestController
public class FailurePointsController {
    private FailurePointsService failurePointsService;

    @Autowired
    public FailurePointsController(FailurePointsService failurePointsService) {
        this.failurePointsService = failurePointsService;
    }

    @GetMapping("/api/v1.0/failurePoints")
    public String[] get()
    {
        return VmMock.failurePoints;
    }
}
