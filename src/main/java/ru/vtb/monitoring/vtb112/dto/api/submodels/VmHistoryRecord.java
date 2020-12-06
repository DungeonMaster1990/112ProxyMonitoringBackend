package ru.vtb.monitoring.vtb112.dto.api.submodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmHistoryRecord {
    private String name;
    private ZonedDateTime finishDate;
    private String role;
    private String description;
}
