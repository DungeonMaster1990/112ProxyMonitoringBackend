package ru.vtb.monitoring.vtb112.db.pg.models.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum ChangeCategory {

    EMERGENCY("Экстренное", 1),
    PLANNED("Плановое", 2);

    private static final Map<String, ChangeCategory> LOOKUP = Arrays.stream(ChangeCategory.values()).collect(Collectors.toMap(
            ChangeCategory::getCategory, changeCategory -> changeCategory
    ));

    private final String category;
    private final int id;

    ChangeCategory(String category, int id) {
        this.category = category;
        this.id = id;
    }

    public static ChangeCategory getCategoryByString(String category) {
        return LOOKUP.get(category);
    }

}
