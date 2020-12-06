package ru.vtb.monitoring.vtb112.db.pg.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedIncidents {

    private long firstCategory;
    private long secondCategory;
    private long thirdCategory;
}
