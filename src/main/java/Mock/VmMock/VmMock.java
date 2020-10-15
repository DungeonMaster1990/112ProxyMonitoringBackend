package Mock.VmMock;

import dto.ViewModels.Enums.BlAccidentStatusType;
import dto.ViewModels.Response.VmPlanResponse;
import dto.ViewModels.Response.VmPlanSectionsResponse;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class VmMock {
    public static VmPlanSectionsResponse[] vmPlanSectionsResponse =
            new VmPlanSectionsResponse[]
    {
        new VmPlanSectionsResponse("1", "Экстренные", 1), new VmPlanSectionsResponse("2", "Плановые", 5)
    };

    public static VmPlanResponse[] vmPlanResponse =
            new VmPlanResponse[]
    {
        new VmPlanResponse("1", "Изменение IM-283501", "Согласование", BlAccidentStatusType.warning, "Описание", new String[]{"Платежи", "Переводы"}, new GregorianCalendar(2020, Calendar.AUGUST, 2)),
        new VmPlanResponse("2", "Изменение IM-283501", "Выполнено", BlAccidentStatusType.normal, "Описание", new String[]{ "Платежи" }, new GregorianCalendar(2016, Calendar.JULY, 5))
    };
}
