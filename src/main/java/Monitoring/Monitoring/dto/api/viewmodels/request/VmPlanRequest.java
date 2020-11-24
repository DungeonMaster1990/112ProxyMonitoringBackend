package Monitoring.Monitoring.dto.api.viewmodels.request;

import Monitoring.Monitoring.dto.api.viewmodels.enums.VmPlanSection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VmPlanRequest extends VmPageRequestBase {

    private String keyword;
    private VmPlanSection planSectionID;

}
