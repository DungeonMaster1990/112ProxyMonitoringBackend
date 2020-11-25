package ru.vtb.monitoring.vtb112.db.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ChangeCategory {

    emergency("Экстренное", 1),
    planned("Плановое", 2);

    private final String category;
    private final int id;

    ChangeCategory(String category, int id) {
        this.category = category;
        this.id = id;
    }

    public static ChangeCategory getCategoryByString(String category) {
        return Arrays.stream(ChangeCategory.values())
                .filter(s -> s.category.equals(category))
                .findFirst()
                .orElse(null);
    }

}
