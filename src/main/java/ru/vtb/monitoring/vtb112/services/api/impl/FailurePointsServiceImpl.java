package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.services.api.interfaces.FailurePointsService;

import java.util.List;
import java.util.Set;

@Service
public class FailurePointsServiceImpl implements FailurePointsService {

    private final IncidentRepository incidentRepository;
    private final Set<String> closedStatuses;

    public FailurePointsServiceImpl(AppConfig appConfig,
                                    IncidentRepository incidentRepository) {
        this.closedStatuses = appConfig.getClosedStatuses();
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<String> getTop10FailurePoints() {
        return incidentRepository.getFailurePoints(closedStatuses, PageRequest.of(0, 10));
    }
}
