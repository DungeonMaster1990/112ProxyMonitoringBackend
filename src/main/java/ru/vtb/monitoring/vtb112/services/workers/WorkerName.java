package ru.vtb.monitoring.vtb112.services.workers;

import lombok.Getter;

public enum WorkerName {
    SM_CHANGES("Changes"),
    SM_INCIDENTS("Incidents"),
    SM_UNAVAILABILITIES("Unavailabilities"),
    VERTICA_SM_RAW_DATA("VerticaSmRawData");

    @Getter
    private final String name;

    WorkerName(String name) {
        this.name = name;
    }
}
