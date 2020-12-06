package ru.vtb.monitoring.vtb112.dto.sm.response.submodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmChangeHeader {
    @JsonAlias("Id")
    private String id;
    @JsonAlias("CurrentPhase")
    private String currentPhase;
    @JsonAlias("Status")
    private String status;
    @JsonAlias("Category")
    private String category;
    @JsonAlias("Subcategory")
    private String subcategory;
    @JsonAlias("BriefDescription")
    private String briefDescription;
    @JsonAlias("RequestedBy")
    private String requestedBy;
    @JsonAlias("RequestedFor")
    private String requestedFor;
    @JsonAlias("AssignDept")
    private String assignDept;
    @JsonAlias("PlannedStartAt")
    private ZonedDateTime plannedStartAt;
    @JsonAlias("PlannedEndAt")
    private ZonedDateTime plannedEndAt;
    @JsonAlias("ForeignId")
    private String foreignId;
}
