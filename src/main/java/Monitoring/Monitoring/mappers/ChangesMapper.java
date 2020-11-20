package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.Changes;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface ChangesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "changeId",            source = "source.header.id")
    @Mapping(target = "currentPhase",        source = "source.header.currentPhase")
    @Mapping(target = "status",              source = "source.header.status")
    @Mapping(target = "category",            source = "source.header.category")
    @Mapping(target = "subcategory",         source = "source.header.subcategory")
    @Mapping(target = "briefDescription",    source = "source.header.briefDescription")
    @Mapping(target = "requestedBy",         source = "source.header.requestedBy")
    @Mapping(target = "requestedFor",        source = "source.header.requestedFor")
    @Mapping(target = "assignDept",          source = "source.header.assignDept")
    @Mapping(target = "plannedStartAt",      source = "source.header.plannedStartAt")
    @Mapping(target = "plannedEndAt",        source = "source.header.plannedEndAt")
    @Mapping(target = "foreignId",           source = "source.header.foreignId")
    @Mapping(target = "affectedServices",    source = "source.middle.affectedServices")
    @Mapping(target = "assets",              source = "source.middle.assets")
    @Mapping(target = "schedOutageStartAt",  source = "source.middle.schedOutageStartAt")
    @Mapping(target = "schedOutageEndAt",    source = "source.middle.schedOutageEndAt")
    @Mapping(target = "downStartAt",         source = "source.middle.downStartAt")
    @Mapping(target = "downEndAt",           source = "source.middle.downEndAt")
    @Mapping(target = "justification",       source = "source.description.justification")
    @Mapping(target = "description",         source = "source.description.description")
    @Mapping(target = "acceptanceComments",  source = "source.description.acceptanceComments")
    @Mapping(target = "plan",                source = "source.description.plan")
    @Mapping(target = "vtbRiskDescription",  source = "source.description.vtbRiskDescription")
    @Mapping(target = "closingComments",     source = "source.close.closingComments")
    Changes mapToChangesResponse(VmSmChange source);

    void updateChange(Changes changes, @MappingTarget Changes updated);

    default String arrayToPlainString(String[] value) {
        return value != null
                ? String.join(System.lineSeparator(), Arrays.asList(value))
                : null;
    }

}
