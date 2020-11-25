package ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VmPlanSection {

    emergency("Экстренное", 1),
    planned("Плановое", 2);

    private final String section;
    private final int id;

    VmPlanSection(String section, int id) {
        this.section = section;
        this.id = id;
    }

    public static VmPlanSection fromValue(int id) {
        return Arrays.stream(VmPlanSection.values())
                .filter(s -> s.id == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
