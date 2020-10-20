package ru.vtb.monitoring.proxy.controllers;

import Mock.VmMock.VmMock;
import dto.viewmodels.request.VmEventRequest;
import dto.viewmodels.response.VmEventResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    @PostMapping("/api/v1.0/events")
    public VmEventResponse[] get(@RequestBody VmEventRequest vmEventRequest)
    {
        return VmMock.vmEventResponse;
    }
}
