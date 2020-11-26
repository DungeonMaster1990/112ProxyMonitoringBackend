package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.FailurePointsService;

@RestController
@RequestMapping(value = PathConstants.FAILURE_POINTS, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class FailurePointsController {

    private FailurePointsService failurePointsService;

    @Autowired
    public FailurePointsController(FailurePointsService failurePointsService) {
        this.failurePointsService = failurePointsService;
    }

    @GetMapping
    public String[] get() {
        return VmMock.failurePoints;
    }
}
