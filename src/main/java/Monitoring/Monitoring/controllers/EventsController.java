package Monitoring.Monitoring.controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmEventRequest;
import dto.ViewModels.Response.VmEventResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    @PostMapping("/api/v1.0/events")
    public VmEventResponse[] get(@RequestBody VmEventRequest vmEventRequest)
    {
        return VmMock.vmEventResponse;
    }
}
