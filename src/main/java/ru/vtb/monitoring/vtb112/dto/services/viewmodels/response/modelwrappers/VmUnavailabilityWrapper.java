package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmUnavailabilityWrapper extends VmBaseResponseWrapper<VmSmUnavailability> {
    public VmUnavailabilityWrapper(Integer count, Integer start, Integer totalCount, String[] messages, String resourceName, Integer returnCode, VmModelWrapper<VmSmUnavailability>[] content) {
        super(count, start, totalCount, messages, resourceName, returnCode, content);
    }
}
