package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VmPageRequestBase {
    private int limit;
    private int page;
}
