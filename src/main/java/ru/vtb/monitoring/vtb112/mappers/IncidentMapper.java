package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlWorkerStatus;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmManager;
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

    // TODO Не возвращать объект manager, если нет имени
    @Mapping(target = "manager", source = "incident")
    @Mapping(target = "workers", source = "incident")
    VmAccidentWorkersResponse mapToWorkersResponse(Incident incident);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "incidentId")
    @Mapping(target = "category", source = "priorityAsCategory")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "statusType", source = "statusType")
    @Mapping(target = "description", source = "title")
    @Mapping(target = "affectedSystems", source = "unavailabilities")
    @Mapping(target = "detectionDate", source = "identedAt")
    VmAccidentResponse mapToApiResponse(Incident incident);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "incidentId")
    @Mapping(target = "category", source = "priorityAsCategory")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "statusType", source = "statusType")
    @Mapping(target = "description", source = "title")
    @Mapping(target = "impactDescription", source = "consequences")
    @Mapping(target = "failurePoint", source = "failurePoint")
    @Mapping(target = "configurationUnit", source = "configurationItemId")
    @Mapping(target = "affectedSystems", source = "unavailabilities")
    @Mapping(target = "startDate", source = "factBeginAt")
    @Mapping(target = "detectionDate", source = "identedAt")
    @Mapping(target = "predictDate", source = "eliminationConsequencesAt")
    @Mapping(target = "conferenceLink", source = "conferenceLink")
    @Mapping(target = "telegramLink", ignore = true)
    VmAccidentInfoResponse mapToApiInfoResponse(Incident incident);

    @Mapping(target = "id", source = "id")
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

    default VmManager mapToManager(Incident incident) {
        String manId = incident.getManagerId();
        if (manId == null || manId.isBlank()) {
            return null;
        }
        VmManager manager = new VmManager();
        manager.setName(incident.getManagerId());
        return manager;
    }
}
