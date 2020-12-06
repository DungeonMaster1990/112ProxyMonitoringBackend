package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.services.api.interfaces.FailurePointsService;

import java.util.List;
import java.util.Set;

@Service
public class FailurePointsServiceImpl implements FailurePointsService {

    private static final Set<String> closedStatuses = Set.of("Закрыто");
    private final IncidentRepository incidentRepository;

    public FailurePointsServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<String> getTop10FailurePoints() {
        return incidentRepository.getFailurePoints(closedStatuses, PageRequest.of(0, 10));
    }
}
