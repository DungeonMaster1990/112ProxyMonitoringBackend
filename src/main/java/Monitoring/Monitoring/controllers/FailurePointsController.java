package Monitoring.Monitoring.controllers;

import Mock.VmMock.VmMock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailurePointsController {
    @GetMapping("/api/v1.0/failure")
    public String[] get()
    {
        return VmMock.failurePoints;
    }
}