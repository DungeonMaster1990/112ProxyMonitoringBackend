package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlMetricsStatus;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.ZonedDateTime;

public class VmMetricInfoResponse {
    private long value;
    private long delta;
    private double deltaPercent;
    private BlMetricsStatus deltaStatus;
    private ZonedDateTime date;

    public VmMetricInfoResponse(long value, long delta, double deltaPercent, BlMetricsStatus deltaStatus, ZonedDateTime date) {
        this.value = value;
        this.delta = delta;
        this.deltaPercent = deltaPercent;
        this.deltaStatus = deltaStatus;
        this.date = date;
    }

    public VmMetricInfoResponse(){}

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getDelta() {
        return delta;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }

    public double getDeltaPercent() {
        return deltaPercent;
    }

    public void setDeltaPercent(double deltaPercent) {
        this.deltaPercent = deltaPercent;
    }

    @JsonGetter
    public BlMetricsStatus getDeltaStatus() {
        return deltaStatus;
    }

    public void setDeltaStatus(BlMetricsStatus deltaStatus) {
        this.deltaStatus = deltaStatus;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
