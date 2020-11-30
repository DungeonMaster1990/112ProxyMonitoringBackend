package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmEventRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmEventResponse;
import ru.vtb.monitoring.vtb112.mocks.VmMock;
import ru.vtb.monitoring.vtb112.services.api.interfaces.EventsService;

@RestController
@RequestMapping(value = PathConstants.EVENTS, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class EventsController {

    private EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping
    public VmEventResponse[] get(@RequestBody VmEventRequest vmEventRequest) {
        return VmMock.vmEventResponse;
    }
}
