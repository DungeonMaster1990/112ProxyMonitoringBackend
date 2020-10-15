package Mock.VmMock;

import dto.ViewModels.Enums.BlAccidentStatusType;
import dto.ViewModels.Enums.BlPlanStatusType;
import dto.ViewModels.Enums.BlWorkerStatus;
import dto.ViewModels.Response.*;
import dto.ViewModels.SubModels.VmHistoryRecord;
import dto.ViewModels.SubModels.VmManager;
import dto.ViewModels.SubModels.VmWorker;

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
        new VmPlanResponse("1", "Изменение IM-283501", "Согласование", BlPlanStatusType.warning, "Описание", new String[]{"Платежи", "Переводы"}, new GregorianCalendar(2020, Calendar.AUGUST, 2)),
        new VmPlanResponse("2", "Изменение IM-283501", "Выполнено", BlPlanStatusType.normal, "Описание", new String[]{ "Платежи" }, new GregorianCalendar(2016, Calendar.JULY, 5))
    };

    public static VmPlanWorkersResponse vmPlanWorkersResponse =
            new VmPlanWorkersResponse(
              new VmManager("Иванов К.А.", "https://www.nj.com/resizer/h8MrN0-Nw5dB5FOmMVGMmfVKFJo=/450x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg", "Начальник управления УАБД ДРИ"),
              new VmWorker[]{
                      new VmWorker("Соколов А. А.", BlWorkerStatus.joined, "Департамент развития инфрастуктуры"),
                      new VmWorker("Иванов А. А.", BlWorkerStatus.pending, "Департамент развития инфрастуктуры"),
                      new VmWorker("Сидоров А. А.", BlWorkerStatus.ready, "Департамент развития инфрастуктуры")
              }
            );

    public static VmPlanHistoryResponse vmPlanHistoryResponse =
            new VmPlanHistoryResponse(
                    new VmHistoryRecord[] { new VmHistoryRecord("Соколов А. А.", new GregorianCalendar(2020, Calendar.AUGUST, 2),"Роль","Описание работы")},
                    new VmHistoryRecord[] { new VmHistoryRecord("Иванов И. И.", new GregorianCalendar(2020, Calendar.AUGUST, 2),"Другая роль","Описание другой работы")}
            );

    public static VmPlanDescriptionResponse[] vmPlanDescriptionResponse =
            new VmPlanDescriptionResponse[]{
                    new VmPlanDescriptionResponse("Для сотрудников","Выявлена недоступность АБС М-Банк, инстанс main. Последствия — в ВТБ Онлайн профиль клиентов, имеющих продукты экс-БМ собираются из кэш. Во время сбоя в ВТБ-онлайн было невозможно проведение операций по части ранее выпущенных карт (только бывшего Банка Москвы)."),
                    new VmPlanDescriptionResponse("Для СМИ", "Ранее зафиксированные проблемы в ВТБ-Онлайн полностью устранены, работа мобильного приложения восстановлена.\nРанее в системе наблюдался кратковременный технический сбой, в ходе которого в адрес клиентов произошла выгрузка ошибочных СМС о ранее проведённых архивных транзакциях, однако никаких списаний денежных средств не производилось.\nПриносим свои извинения за неудобства, наши IT специалисты делают все возможное для усовершенствования онлайн-сервисов.")
    };

    public static VmAccidentResponse[] vmAccidentResponse =
            new VmAccidentResponse[]
                    {
                            new VmAccidentResponse(
                                    "1",
                                    "Авария IM-216836",
                                    10,
                                    "Описание",
                                    new String[] { "Платежи","Переводы" },
                                    new GregorianCalendar(2016, Calendar.JULY, 6),
                                    "Устранение последствий",
                                    BlAccidentStatusType.warning)
                    };

    public static VmNewAccidentResponse vmNewAccidentResponse =
            new VmNewAccidentResponse("1", "Авария IM-216836");

    public static VmAccidentInfoResponse vmAccidentInfoResponse =
            new VmAccidentInfoResponse(
                    "1",
                    "Авария IM-216836",
                    10,
                    "Устранение последствий",
                    BlAccidentStatusType.warning,
                    "Описание",
                    "Описание последствия",
                    "М-Банк",
                    "М-Банк",
                    new String[] { "Платежи", "Переводы" },
                    new GregorianCalendar(2016, Calendar.JULY, 26),
                    new GregorianCalendar(2016, Calendar.JULY, 20),
                    new GregorianCalendar(2016, Calendar.JULY, 21),
                    "https://bankvtb.webex.com/meet/xxx",
                    "https://t.me/vtb"
            );

    public static VmAccidentWorkersResponse vmAccidentWorkersResponse =
            new VmAccidentWorkersResponse(
                    new VmManager("Иванов К.А.", "https://www.nj.com/resizer/h8MrN0-Nw5dB5FOmMVGMmfVKFJo=/450x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg", "Начальник управления УАБД ДРИ"),
                    new VmWorker[]{
                            new VmWorker("Соколов А. А.", BlWorkerStatus.joined, "Департамент развития инфрастуктуры"),
                            new VmWorker("Иванов А. А.", BlWorkerStatus.pending, "Департамент развития инфрастуктуры"),
                            new VmWorker("Сидоров А. А.", BlWorkerStatus.ready, "Департамент развития инфрастуктуры")
                    }
            );

    public static VmAccidentHistoryResponse vmAccidentHistoryResponse =
            new VmAccidentHistoryResponse(
                    new VmHistoryRecord[] { new VmHistoryRecord("Соколов А. А.", new GregorianCalendar(2020, Calendar.AUGUST, 2),"Роль","Описание работы")},
                    new VmHistoryRecord[] { new VmHistoryRecord("Иванов И. И.", new GregorianCalendar(2020, Calendar.AUGUST, 2),"Другая роль","Описание другой работы")}
            );

    public static VmAccidentDescriptionResponse[] vmAccidentDescriptionResponse =
            new VmAccidentDescriptionResponse[]{
                    new VmAccidentDescriptionResponse("Для сотрудников","Выявлена недоступность АБС М-Банк, инстанс main. Последствия — в ВТБ Онлайн профиль клиентов, имеющих продукты экс-БМ собираются из кэш. Во время сбоя в ВТБ-онлайн было невозможно проведение операций по части ранее выпущенных карт (только бывшего Банка Москвы)."),
                    new VmAccidentDescriptionResponse("Для СМИ", "Ранее зафиксированные проблемы в ВТБ-Онлайн полностью устранены, работа мобильного приложения восстановлена.\nРанее в системе наблюдался кратковременный технический сбой, в ходе которого в адрес клиентов произошла выгрузка ошибочных СМС о ранее проведённых архивных транзакциях, однако никаких списаний денежных средств не производилось.\nПриносим свои извинения за неудобства, наши IT специалисты делают все возможное для усовершенствования онлайн-сервисов.")
            };
}
