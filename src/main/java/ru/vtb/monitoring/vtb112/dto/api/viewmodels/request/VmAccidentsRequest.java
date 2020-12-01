package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VmAccidentsRequest extends VmPageRequestBase {

    private ZonedDateTime startDate;
    private List<String> affectedSystems;
    private List<String> failurePoints;
    @Nullable
    private String systemId;
    @Nullable
    private String planTypeId;
    private String keyword;
}
