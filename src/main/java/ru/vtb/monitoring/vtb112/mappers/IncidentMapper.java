package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlWorkerStatus;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmWorker;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmIncident;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IncidentMapper extends ResponseMapper<Incident, VmSmIncident> {

    @Mapping(target = "incidentId", source = "source.id")
    @Mapping(target = "unavailabilities", ignore = true)
    @Mapping(target = "notificationSent", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "managerId", source = "specialistId")
    @Override
    Incident mapToResponse(VmSmIncident source);

    @Mapping(target = "id", ignore = true)
    void updateIncident(Incident incident, @MappingTarget Incident updated);

    @Mapping(target = "name", constant = "Для сотрудников")
    @Mapping(target = "value", source = "description")
    VmAccidentDescriptionResponse mapToDescriptionResponse(Incident source);

    @Mapping(target = "manager.name", source = "managerId")
    @Mapping(target = "workers", source = "incident")
    VmAccidentWorkersResponse mapToWorkersResponse(Incident incident);

    @Mapping(target = "name", source = "incidentId")
    @Mapping(target = "category", source = "priorityAsCategory")
    @Mapping(target = "detectionDate", source = "identedAt")
    @Mapping(target = "affectedSystems", source = "unavailabilities")
    VmAccidentResponse mapToApiResponse(Incident incident);

    @Mapping(target = "name", source = "incidentId")
    @Mapping(target = "category", source = "priorityAsCategory")
    @Mapping(target = "impactDescription", source = "consequences")
    @Mapping(target = "configurationUnit", source = "configurationItemId")
    @Mapping(target = "startDate", source = "createdAt")
    @Mapping(target = "detectionDate", source = "identedAt")
    @Mapping(target = "predictDate", source = "expiredAt")
    @Mapping(target = "telegramLink", ignore = true)
    @Mapping(target = "affectedSystems", source = "unavailabilities")
    VmAccidentInfoResponse mapToApiInfoResponse(Incident incident);

    @Mapping(target = "name", source = "incidentId")
    VmNewAccidentResponse mapToApiNewResponse(Incident incident);

    default List<String> mapUnavailabilities(List<Unavailabilities> unavailabilities) {
        return unavailabilities == null ? Collections.emptyList() :
                unavailabilities.stream().map(Unavailabilities::getServiceName).collect(Collectors.toList());
    }

    default String mapDescriptionResolutionConsequences(String[] value) {
        return value == null ? null :
                Arrays.stream(value).filter(Objects::nonNull)
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    default List<VmWorker> mapToWorkers(Incident incident) {
        if (incident.getAssigneeId() == null) {
            return Collections.emptyList();
        }
        VmWorker worker = new VmWorker();
        worker.setName(incident.getAssigneeId());
        worker.setRole(incident.getGroupId());
        worker.setStatus(BlWorkerStatus.joined);
        return Collections.singletonList(worker);
    }
}
