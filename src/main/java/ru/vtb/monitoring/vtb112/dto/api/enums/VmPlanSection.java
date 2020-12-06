package ru.vtb.monitoring.vtb112.dto.api.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VmPlanSection {

    EMERGENCY("Экстренное", 1),
    STANDARD("Стандартное", 2),
    NORMAL("Нормальное", 3);

    private final String section;
    private final int id;

    VmPlanSection(String section, int id) {
        this.section = section;
        this.id = id;
    }

    public static VmPlanSection fromId(int id) {
        return Arrays.stream(VmPlanSection.values())
                .filter(s -> s.id == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static VmPlanSection fromSection(String section) {
        return Arrays.stream(VmPlanSection.values())
                .filter(s -> s.section.equals(section))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
