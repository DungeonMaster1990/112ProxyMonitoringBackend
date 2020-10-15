package Mock.VmMock;

import dto.ViewModels.Response.VmPlanSectionsResponse;

public class VmMock {
    public static VmPlanSectionsResponse[] vmPlanSectionsResponse =
            new VmPlanSectionsResponse[]
    {
        new VmPlanSectionsResponse("1", "Экстренные", 1), new VmPlanSectionsResponse("2", "Плановые", 5)
    };
}
