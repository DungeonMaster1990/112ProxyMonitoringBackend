package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlAccidentStatusType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmAccidentInfoResponse {
    private String id;
    private String name;
    private Integer category;
    private String status;
    private BlAccidentStatusType statusType;
    private String description;
    private String impactDescription;
    private String failurePoint;
    private String configurationUnit;
    private List<String> affectedSystems;
    private ZonedDateTime startDate;
    private ZonedDateTime detectionDate;
    private ZonedDateTime predictDate;
    private String conferenceLink;
    private String telegramLink;
}
