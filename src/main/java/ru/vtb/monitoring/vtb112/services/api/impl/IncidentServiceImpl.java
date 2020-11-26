package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.monitoring.vtb112.db.models.AffectedSystem;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.converters.IncidentStatusConverter;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.*;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels.VmManager;
import ru.vtb.monitoring.vtb112.services.api.interfaces.IncidentService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentDAO;

    public IncidentServiceImpl(IncidentRepository incidentDAO) {
        this.incidentDAO = incidentDAO;
    }

    @Override
    @Transactional
    public List<VmAccidentResponse> getAccidents(VmAccidentsRequest request) {
        var paging = PageRequest.of(request.getPage(), request.getLimit());
        var supportedCategories = Arrays.asList(1, 2);
        return incidentDAO.allByCriteria(supportedCategories,
                request.getAffectedSystems(),
                request.getStartDate(),
                request.getKeyword(),
                paging)
                .stream()
                .map(incident -> new VmAccidentResponse(
                        incident.getId().toString(),
                        incident.getIncidentId(),
                        incident.getCategory() == null ? 0 : incident.getCategory(),
                        IncidentStatusConverter.convertToStatus(
                                incident.getStatus(),
                                incident.getFactEndAt(),
                                incident.getExpiredAt()
                        ),
                        IncidentStatusConverter.convertToStatusType(incident.getStatus()),
                        incident.getDescription(),
                        incident.getAffectedSystems()
                                .stream()
                                .map(AffectedSystem::getName)
                                .collect(Collectors.toList()),
                        incident.getIdentedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public VmNewAccidentResponse getNewAccident() {
        return incidentDAO.findMaxByCreatedDate()
                .map(incident -> new VmNewAccidentResponse(
                        incident.getId().toString(),
                        incident.getIncidentId()))
                .orElse(null);
    }

    @Override
    @Transactional
    public VmAccidentInfoResponse getAccidentInfo(Integer id) {
        return incidentDAO.findById(id)
                .map(incident -> new VmAccidentInfoResponse(
                        incident.getId().toString(),
                        incident.getIncidentId(),
                        incident.getCategory() == null ? 0 : incident.getCategory(),
                        IncidentStatusConverter.convertToStatus(
                                incident.getStatus(),
                                incident.getFactEndAt(),
                                incident.getExpiredAt()
                        ),
                        IncidentStatusConverter.convertToStatusType(incident.getStatus()),
                        incident.getDescription(),
                        incident.getImpact(),
                        incident.getFailurePoint(),
                        "М-Банк",
                        incident.getAffectedSystems()
                                .stream()
                                .map(AffectedSystem::getName)
                                .collect(Collectors.toList()),
                        incident.getCreatedAt(),
                        incident.getIdentedAt(),
                        incident.getExpiredAt(),
                        "https://bankvtb.webex.com/meet/xxx",
                        "https://t.me/vtb"))
                .orElse(null);
    }

    @Override
    public VmAccidentWorkersResponse getWorkers(Integer id) {
        return incidentDAO.findById(id)
                .map(incident -> new VmAccidentWorkersResponse(
                        new VmManager(incident.getSpecialistId(), null, null),
                        Collections.emptyList()))
                .orElse(null);
    }

    @Override
    public List<VmAccidentDescriptionResponse> getAccidentDescriptions(Integer id) {
        return incidentDAO.findById(id)
                .map(incident -> Collections.singletonList(
                        new VmAccidentDescriptionResponse(incident.getDescription(), null)))
                .orElse(null);
    }
}
