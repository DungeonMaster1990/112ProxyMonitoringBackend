package Monitoring.Monitoring.dto.api.viewmodels.response;

import Monitoring.Monitoring.dto.api.viewmodels.enums.BlMetricsStatus;

public class VmMetricsResponse {
    private String id;
    private String name;
    private Boolean mine;
    private String value;
    private long delta;
    private double deltaPercent;
    private BlMetricsStatus deltaStatus;
    private double totalPercent;

    public VmMetricsResponse(String id, String name, Boolean mine, String value, long delta, double deltaPercent, BlMetricsStatus deltaStatus, double totalPercent) {
        this.id = id;
        this.name = name;
        this.mine = mine;
        this.value = value;
        this.delta = delta;
        this.deltaPercent = deltaPercent;
        this.deltaStatus = deltaStatus;
        this.totalPercent = totalPercent;
    }

    public VmMetricsResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMine() {
        return mine;
    }

    public void setMine(Boolean mine) {
        this.mine = mine;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public double getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(double totalPercent) {
        this.totalPercent = totalPercent;
    }
}
