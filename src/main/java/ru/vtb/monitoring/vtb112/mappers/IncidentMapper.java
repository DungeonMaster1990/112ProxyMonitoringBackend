package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.Unavailabilities;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlAccidentStatusType;
import ru.vtb.monitoring.vtb112.dto.api.enums.BlWorkerStatus;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmWorker;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmIncident;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    @Mapping(target = "status", source = "incident")
    @Mapping(target = "statusType", source = "status")
    @Mapping(target = "detectionDate", source = "identedAt")
    @Mapping(target = "affectedSystems", source = "unavailabilities")
    VmAccidentResponse mapToApiResponse(Incident incident);

    @Mapping(target = "name", source = "incidentId")
    @Mapping(target = "category", source = "priorityAsCategory")
    @Mapping(target = "status", source = "incident")
    @Mapping(target = "statusType", source = "status")
    @Mapping(target = "impactDescription", source = "consequences")
    @Mapping(target = "configurationUnit", source = "configurationItemId")
    @Mapping(target = "startDate", source = "createdAt")
    @Mapping(target = "detectionDate", source = "identedAt")
    @Mapping(target = "predictDate", source = "expiredAt")
    //@Mapping(target = "telegramLink", constant = "https://t.me/vtb")
    @Mapping(target = "affectedSystems", source = "unavailabilities")
    VmAccidentInfoResponse mapToApiInfoResponse(Incident incident);

    @Mapping(target = "name", source = "incidentId")
    VmNewAccidentResponse mapToApiNewResponse(Incident incident);

    default BlAccidentStatusType mapPlanStatusType(String status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case "Назначено" -> BlAccidentStatusType.critical;
            case "В работе" -> BlAccidentStatusType.warning;
            default -> BlAccidentStatusType.normal;
        };
    }

    default List<String> mapUnavailabilities(List<Unavailabilities> unavailabilities) {
        return unavailabilities == null ? Collections.emptyList() :
                unavailabilities.stream().map(Unavailabilities::getServiceName).collect(Collectors.toList());
    }

    default String convertToStatus(Incident incident) {
        if ("Назначено".equals(incident.getStatus()) && incident.getEliminationConsequencesAt() != null) {
            return "Устранение последствий";
        }
        return incident.getStatus();
    }

    default String mapDescriptionOrResolution(String[] value) {
        return value != null
                ? String.join(System.lineSeparator(), Arrays.asList(value))
                : null;
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
