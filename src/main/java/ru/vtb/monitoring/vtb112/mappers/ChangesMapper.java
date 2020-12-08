package ru.vtb.monitoring.vtb112.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vtb.monitoring.vtb112.db.pg.models.Changes;
import ru.vtb.monitoring.vtb112.db.pg.models.dto.GroupedChanges;
import ru.vtb.monitoring.vtb112.dto.api.response.*;
import ru.vtb.monitoring.vtb112.dto.api.submodels.VmWorker;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmChange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ChangesMapper extends ResponseMapper<Changes, VmSmChange> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "allAffected", ignore = true)
    @Mapping(target = "changeId", source = "source.header.id")
    @Mapping(target = "currentPhase", source = "source.header.currentPhase")
    @Mapping(target = "status", source = "source.header.status")
    @Mapping(target = "category", source = "source.header.category")
    @Mapping(target = "subcategory", source = "source.header.subcategory")
    @Mapping(target = "briefDescription", source = "source.header.briefDescription")
    @Mapping(target = "requestedBy", source = "source.header.requestedBy")
    @Mapping(target = "requestedFor", source = "source.header.requestedFor")
    @Mapping(target = "assignDept", source = "source.header.assignDept")
    @Mapping(target = "plannedStartAt", source = "source.header.plannedStartAt")
    @Mapping(target = "plannedEndAt", source = "source.header.plannedEndAt")
    @Mapping(target = "foreignId", source = "source.header.foreignId")
    @Mapping(target = "affectedServices", source = "source.middle.affectedServices")
    @Mapping(target = "assets", source = "source.middle.assets")
    @Mapping(target = "schedOutageStartAt", source = "source.middle.schedOutageStartAt")
    @Mapping(target = "schedOutageEndAt", source = "source.middle.schedOutageEndAt")
    @Mapping(target = "downStartAt", source = "source.middle.downStartAt")
    @Mapping(target = "downEndAt", source = "source.middle.downEndAt")
    @Mapping(target = "justification", source = "source.description.justification")
    @Mapping(target = "description", source = "source.description.description")
    @Mapping(target = "acceptanceComments", source = "source.description.acceptanceComments")
    @Mapping(target = "plan", source = "source.description.plan")
    @Mapping(target = "riskDescription", source = "source.description.riskDescription")
    @Mapping(target = "closingComments", source = "source.close.closingComments")
    @Override
    Changes mapToResponse(VmSmChange source);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "changeId")
    @Mapping(target = "status", source = "currentPhase")
    @Mapping(target = "statusType", source = "statusType")
    @Mapping(target = "description", source = "briefDescription")
    @Mapping(target = "impactDescription", source = "riskDescription")
    @Mapping(target = "degradationRate", source = "initialImpact")
    @Mapping(target = "configurationUnit", ignore = true)
    @Mapping(target = "affectedSystems", source = "allAffected")
    @Mapping(target = "startDate", source = "plannedStartAt")
    @Mapping(target = "finishDate", source = "plannedEndAt")
    @Mapping(target = "startDownDate", source = "schedOutageStartAt")
    @Mapping(target = "finishDownDate", source = "schedOutageEndAt")
    VmPlanInfoResponse mapToInfoResponse(Changes source);

    @Mapping(target = "name", constant = "Для сотрудников")
    // TODO check what to send. mb add briefDescription, or all types of descriptions
    @Mapping(target = "value", source = "description")
    VmPlanDescriptionResponse mapToDescriptionResponse(Changes source);

    // TODO Не возвращать объект manager, если нет имени
    @Mapping(target = "manager.name", source = "requestedBy")
    @Mapping(target = "workers", source = "requestedFor")
    VmPlanWorkersResponse mapToWorkersResponse(Changes source);

    @Mapping(target = "id", source = "source.category.id")
    @Mapping(target = "name", source = "source.category.section")
    @Mapping(target = "count", source = "count")
    VmPlanSectionsResponse mapToVmPlanSections(GroupedChanges source);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "changeId")
    @Mapping(target = "status", source = "currentPhase")
    @Mapping(target = "statusType", source = "statusType")
    @Mapping(target = "description", source = "briefDescription")
    @Mapping(target = "affectedSystems", source = "allAffected")
    @Mapping(target = "startDate", source = "plannedStartAt")
    VmPlanResponse mapToVmPlan(Changes source);

    @Mapping(target = "id", ignore = true)
    void updateChange(Changes changes, @MappingTarget Changes updated);

    default List<VmWorker> mapToWorkers(String requestedFor) {
        if (requestedFor == null) {
            return Collections.emptyList();
        }
        VmWorker worker = new VmWorker();
        worker.setName(requestedFor);
        return Collections.singletonList(worker);
    }

    default String arrayToPlainString(String[] value) {
        return value == null ? null :
                Arrays.stream(value).filter(Objects::nonNull)
                        .collect(Collectors.joining(Changes.DELIMITER));
    }
}
