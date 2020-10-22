package Monitoring.Monitoring.dto.viewmodels.response;

import Monitoring.Monitoring.dto.viewmodels.enums.BlMetricsStatus;

import java.util.Calendar;

public class VmMetricInfoResponse {
    private long value;
    private long delta;
    private double deltaPercent;
    private BlMetricsStatus deltaStatus;
    private Calendar date;

    public VmMetricInfoResponse(long value, long delta, double deltaPercent, BlMetricsStatus deltaStatus, Calendar date) {
        this.value = value;
        this.delta = delta;
        this.deltaPercent = deltaPercent;
        this.deltaStatus = deltaStatus;
        this.date = date;
    }

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

    public BlMetricsStatus getDeltaStatus() {
        return deltaStatus;
    }

    public void setDeltaStatus(BlMetricsStatus deltaStatus) {
        this.deltaStatus = deltaStatus;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
