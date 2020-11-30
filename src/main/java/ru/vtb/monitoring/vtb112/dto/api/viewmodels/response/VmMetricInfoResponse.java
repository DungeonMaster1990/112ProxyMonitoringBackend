package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import lombok.Builder;
import lombok.Data;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlMetricsStatus;

import java.time.ZonedDateTime;

@Data
@Builder
public class VmMetricInfoResponse {
    private long value;
    private long delta;
    private double deltaPercent;
    private BlMetricsStatus deltaStatus;
    private ZonedDateTime date;
}
