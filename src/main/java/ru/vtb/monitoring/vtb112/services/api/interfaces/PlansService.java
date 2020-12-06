package ru.vtb.monitoring.vtb112.services.api.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.dto.api.response.*;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public interface PlansService {

    VmPlanInfoResponse getInfo(Integer id);

    List<VmPlanDescriptionResponse> getDescriptions(Integer id);

    VmPlanWorkersResponse getWorkers(Integer id);

    List<VmPlanSectionsResponse> getSections(ZonedDateTime startDate, ZonedDateTime endDate);

    List<VmPlanResponse> getSection(String category, String keyword, Pageable paging);

}
