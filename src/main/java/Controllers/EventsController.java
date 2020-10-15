package Controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmEventRequest;
import dto.ViewModels.Response.VmEventResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v{1.0}/events")
public class EventsController {
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public VmEventResponse[] get(@RequestBody VmEventRequest vmEventRequest)
    {
        return VmMock.vmEventResponse;
    }
}
