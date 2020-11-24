package Monitoring.Monitoring.db.models.dto;

import Monitoring.Monitoring.db.models.enums.ChangeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedChanges {

    private ChangeCategory category;
    private int count;

    public GroupedChanges(String category, long count) {
        this.category = ChangeCategory.getCategoryByString(category);
        this.count = Long.valueOf(count).intValue();
    }

}
