package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.VmPlanSection;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VmPlanRequest extends VmPageRequestBase {

    private String keyword;
    private VmPlanSection planSectionID;

}
