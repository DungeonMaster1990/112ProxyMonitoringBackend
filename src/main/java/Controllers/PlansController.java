package Controllers;

import Mock.VmMock.VmMock;
import dto.ViewModels.Request.VmPlanSectionRequest;
import dto.ViewModels.Response.VmPlanSectionsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v{1.0}/plans")
public class PlansController {

    @PostMapping(value = "/sections", consumes = "application/json", produces = "application/json")
    public VmPlanSectionsResponse[] GetPlanSections(@RequestBody VmPlanSectionRequest vmPlanSectionRequest)
    {
        return VmMock.vmPlanSectionsResponse;
    }
}
