package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.VmMock.VmMock;
import Monitoring.Monitoring.dto.viewmodels.request.VmEventRequest;
import Monitoring.Monitoring.dto.viewmodels.response.VmEventResponse;
import Monitoring.Monitoring.services.interfaces.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {
    private EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/api/v1.0/events")
    public VmEventResponse[] get(@RequestBody VmEventRequest vmEventRequest)
    {
        return VmMock.vmEventResponse;
    }
}
