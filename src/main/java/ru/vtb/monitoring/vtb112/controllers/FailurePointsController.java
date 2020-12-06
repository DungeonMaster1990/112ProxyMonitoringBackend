package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.services.api.interfaces.FailurePointsService;

import java.util.List;

@RestController
@RequestMapping(value = PathConstants.FAILURE_POINTS, produces = MediaType.APPLICATION_JSON_VALUE)
public class FailurePointsController {

    private final FailurePointsService failurePointsService;

    @Autowired
    public FailurePointsController(FailurePointsService failurePointsService) {
        this.failurePointsService = failurePointsService;
    }

    @GetMapping
    public List<String> get() {
        return failurePointsService.getTop10FailurePoints();
    }
}
