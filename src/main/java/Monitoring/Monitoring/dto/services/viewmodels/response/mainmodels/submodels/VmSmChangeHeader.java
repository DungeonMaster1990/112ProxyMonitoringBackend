package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.submodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
