package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedUnavailabilities;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UnavailabilitiesRepository;
import ru.vtb.monitoring.vtb112.dto.api.response.VmSystemResponse;
import ru.vtb.monitoring.vtb112.mappers.SystemMapper;
import ru.vtb.monitoring.vtb112.services.api.interfaces.SystemsService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SystemsServiceImpl implements SystemsService {

    private static final Set<String> closedStatuses = Set.of("Завершено", "Контроль", "Закрыто");
    private final IncidentRepository incidentRepository;
    private final SystemMapper systemMapper;
    private final UnavailabilitiesRepository unavailabilitiesRepository;

    public SystemsServiceImpl(IncidentRepository incidentRepository, SystemMapper systemMapper, UnavailabilitiesRepository unavailabilitiesRepository) {
        this.incidentRepository = incidentRepository;
        this.systemMapper = systemMapper;
        this.unavailabilitiesRepository = unavailabilitiesRepository;
    }

    @Override
    public List<VmSystemResponse> get(int page, int limit) {
        return incidentRepository.countActiveIncidents(closedStatuses).stream()
                .map(systemMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTop10Unavailabilities() {
        return unavailabilitiesRepository.getTopUnavailabilities(closedStatuses, PageRequest.of(0, 10)).stream()
                .map(GroupedUnavailabilities::getService)
                .collect(Collectors.toList());
    }
}
