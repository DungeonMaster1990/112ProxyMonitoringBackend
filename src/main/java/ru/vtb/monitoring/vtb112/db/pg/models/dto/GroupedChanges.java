package ru.vtb.monitoring.vtb112.db.pg.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.enums.VmPlanSection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedChanges {

    private VmPlanSection category;
    private int count;

    public GroupedChanges(String category, long count) {
        this.category = VmPlanSection.fromSection(category);
        this.count = (int) count;
    }

}
