package Controllers;

import Mock.VmMock.VmMock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v{1.0}/failure")
public class FailurePointsController {
    @GetMapping(value = "/", consumes = "application/json", produces = "application/json")
    public String[] get()
    {
        return VmMock.failurePoints;
    }
}
