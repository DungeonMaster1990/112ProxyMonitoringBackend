package ru.vtb.monitoring.vtb112.db.models.enums;

import java.util.Arrays;

public enum Status {

    assigned("Назначено"),
    in_process("В работе"),
    documenting("Документирование"),
    completed("Завершено");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status getStatusByString(String status) {
        return Arrays.stream(Status.values())
                .filter(s -> s.status.equals(status))
                .findFirst()
                .orElse(null);
    }
}
