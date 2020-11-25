package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VmPlanSectionRequest {

    @Nullable
    private ZonedDateTime startDate;
    @Nullable
    private ZonedDateTime finishDate;

}
