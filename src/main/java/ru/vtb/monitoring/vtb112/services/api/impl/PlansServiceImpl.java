package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.ChangesRepository;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.mappers.ChangesMapper;
import ru.vtb.monitoring.vtb112.services.api.interfaces.PlansService;
import ru.vtb.monitoring.vtb112.utils.DateUtil;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlansServiceImpl implements PlansService {

    private final ChangesRepository changesRepository;
    private final ChangesMapper changesMapper;
    private static final Set<String> phasesForCurrent = Set.of("Реализация", "Анализ результатов реализации", "Закрытие");
    private static final Set<String> phasesForPlanned = Set.of("Планирование", "Согласование", "Авторизация", "Реализация", "Анализ результатов реализации", "Закрытие");

    public PlansServiceImpl(ChangesRepository changesRepository, ChangesMapper changesMapper) {
        this.changesRepository = changesRepository;
        this.changesMapper = changesMapper;
    }

    @Override
    public VmPlanInfoResponse getInfo(Integer id) {
        return changesMapper.mapToInfoResponse(changesRepository.findById(id).orElse(null));
    }

    @Override
    public List<VmPlanDescriptionResponse> getDescriptions(Integer id) {
        return Collections.singletonList(changesMapper.mapToDescriptionResponse(
                changesRepository.findById(id).orElse(null)));
    }

    @Override
    public VmPlanWorkersResponse getWorkers(Integer id) {
        return changesMapper.mapToWorkersResponse(changesRepository.findById(id).orElse(null));
    }

    @Override
    public List<VmPlanSectionsResponse> getSections(ZonedDateTime startDate, ZonedDateTime endDate) {
        var changes = startDate == null && endDate == null ?
                changesRepository.getCurrentGroupedChanges(DateUtil.now(), phasesForCurrent) :
                changesRepository.getPlannedGroupedChanges(startDate, endDate, phasesForPlanned);
        return changes.stream()
                .map(changesMapper::mapToVmPlanSections)
                .collect(Collectors.toList());
    }

    @Override
    public List<VmPlanResponse> getSection(ZonedDateTime startDate,
                                           ZonedDateTime endDate,
                                           String category,
                                           String keyword,
                                           Pageable paging) {
        var changes = startDate == null && endDate == null
                ? StringUtils.isEmpty(keyword)
                    ? changesRepository.getCurrentChanges(DateUtil.now(), phasesForCurrent, category, paging)
                    : changesRepository.getCurrentChanges(DateUtil.now(), phasesForCurrent, category, keyword, paging)
                : StringUtils.isEmpty(keyword)
                    ? changesRepository.getPlannedChanges(startDate, endDate, phasesForPlanned, category, paging)
                    : changesRepository.getPlannedChanges(startDate, endDate, phasesForPlanned, category, keyword, paging);
        return changes.stream()
                .map(changesMapper::mapToVmPlan)
                .collect(Collectors.toList());
    }
}
