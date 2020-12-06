package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.dto.api.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.mappers.IncidentMapper;
import ru.vtb.monitoring.vtb112.services.api.interfaces.IncidentService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentDAO;
    private final List<String> supportedCategories;
    private final IncidentMapper incidentMapper;

    public IncidentServiceImpl(AppConfig appConfig, IncidentRepository incidentDAO, IncidentMapper incidentMapper) {
        this.supportedCategories = appConfig.getSupportedCategories();
        this.incidentDAO = incidentDAO;
        this.incidentMapper = incidentMapper;
    }

    @Override
    @Transactional
    public List<VmAccidentResponse> getAccidents(VmAccidentsRequest request) {
        var paging = PageRequest.of(request.getPage() - 1, request.getLimit());
        return incidentDAO.allByCriteria(supportedCategories,
                request.getAffectedSystems(),
                request.getStartDate(),
                request.getKeyword(),
                paging)
                .stream()
                .map(incidentMapper::mapToApiResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VmNewAccidentResponse getNewAccident() {
        return incidentDAO.findMaxByCreatedDate()
                .map(incidentMapper::mapToApiNewResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public VmAccidentInfoResponse getAccidentInfo(Integer id) {
        return incidentDAO.findById(id)
                .map(incidentMapper::mapToApiInfoResponse)
                .orElse(null);
    }

    @Override
    public VmAccidentWorkersResponse getWorkers(Integer id) {
        return incidentDAO.findById(id)
                .map(incidentMapper::mapToWorkersResponse)
                .orElse(null);
    }

    @Override
    public List<VmAccidentDescriptionResponse> getAccidentDescriptions(Integer id) {
        return incidentDAO.findById(id)
                .map(incident -> Collections.singletonList(
                        incidentMapper.mapToDescriptionResponse(incident)))
                .orElse(Collections.emptyList());
    }
}
