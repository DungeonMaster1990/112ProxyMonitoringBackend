package Monitoring.Monitoring.services.api.impl;

import Monitoring.Monitoring.db.repositories.interfaces.ChangesRepository;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanDescriptionResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanInfoResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanSectionsResponse;
import Monitoring.Monitoring.dto.api.viewmodels.response.VmPlanWorkersResponse;
import Monitoring.Monitoring.mappers.ChangesMapper;
import Monitoring.Monitoring.services.api.interfaces.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlansServiceImpl implements PlansService {

    @Autowired
    ChangesRepository changesRepository;

    @Autowired
    ChangesMapper changesMapper;

    @Override
    public VmPlanInfoResponse getInfo(Integer id) {
        return changesMapper.mapToInfoResponse(changesRepository.findById(id).orElse(null));
    }

    @Override
    public List<VmPlanDescriptionResponse> getDescriptions(Integer id){
        return Collections.singletonList(changesMapper.mapToDescriptionResponse(
                changesRepository.findById(id).orElse(null)
                )
        );
    }

    @Override
    public VmPlanWorkersResponse getWorkers(Integer id) {
        return changesMapper.mapToVmPlanWorkersResponse(changesRepository.findById(id).orElse(null));
    }

    @Override
    public List<VmPlanSectionsResponse> getSections(ZonedDateTime startDate, ZonedDateTime endDate) {
        return changesRepository.getGroupedChanges(startDate, endDate)
                .stream()
                .filter(gc -> gc.getCategory() != null)
                .map(changesMapper::mapToVmPlanSections)
                .collect(Collectors.toList());
    }

    @Override
    public List<VmPlanResponse> getSection(String category, String keyword, Pageable paging) {
        return changesRepository.findByCategoryAndChangeIdContaining(category, keyword, paging)
                .stream()
                .map(changesMapper::mapToVmPlan)
                .collect(Collectors.toList());
    }
}
