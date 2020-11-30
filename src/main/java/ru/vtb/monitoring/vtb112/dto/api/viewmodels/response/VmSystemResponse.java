package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmSystemResponse {
    private String id;
    private String name;
    private Boolean mine;
    private int criticalAccidents;
    private int majorAccidents;
    private int minorAccidents;
}
