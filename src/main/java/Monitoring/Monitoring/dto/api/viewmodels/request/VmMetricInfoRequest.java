package Monitoring.Monitoring.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VmMetricInfoRequest {
    private String id;
    private ZonedDateTime startDate;
    private ZonedDateTime finishDate;
}
