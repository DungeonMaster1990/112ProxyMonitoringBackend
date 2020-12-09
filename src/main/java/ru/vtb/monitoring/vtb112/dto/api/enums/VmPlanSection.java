package ru.vtb.monitoring.vtb112.dto.api.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VmPlanSection {

    EMERGENCY("Экстренное", "Экстренное", 1),
    STANDARD("Стандартное", "Стандартизованное", 2),
    NORMAL("Нормальное", "Плановое", 3);


    private final String section;
    private final String outputSectionName;
    private final int id;

    VmPlanSection(String section, String outputSectionName, int id) {
        this.section = section;
        this.outputSectionName = outputSectionName;
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
