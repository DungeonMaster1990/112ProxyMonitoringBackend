package ru.vtb.monitoring.vtb112.db.models.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Status {

    ASSIGNED("Назначено"),
    IN_PROCESS("В работе"),
    DOCUMENTING("Документирование"),
    COMPLETED("Завершено");

    private static final Map<String, Status> LOOKUP = Arrays.stream(Status.values()).collect(Collectors.toMap(
            Status::getText, status -> status
    ));

    private final String text;

    Status(String text) {
        this.text = text;
    }

    public static Status getStatusByString(String status) {
        return LOOKUP.get(status);
    }

    public String getText() {
        return text;
    }
}
