package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmEventRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmEventResponse;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.EventsService;

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
