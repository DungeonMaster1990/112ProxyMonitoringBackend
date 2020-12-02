package ru.vtb.monitoring.vtb112.db.pg.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.db.pg.models.enums.ChangeCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedChanges {

    private ChangeCategory category;
    private int count;

    public GroupedChanges(String category, long count) {
        this.category = ChangeCategory.getCategoryByString(category);
        this.count = (int) count;
    }

}
