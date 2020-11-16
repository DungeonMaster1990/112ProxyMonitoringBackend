package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlAccidentStatusType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmAccidentResponse {
    private String id;
    private String name;
    private int category;
    private String status;
    private BlAccidentStatusType statusType;
    private String description;
    private List<String> affectedSystems;
    private ZonedDateTime detectionDate;
}
