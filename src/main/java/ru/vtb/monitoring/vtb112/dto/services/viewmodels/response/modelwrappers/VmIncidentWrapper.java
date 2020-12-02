package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmIncidentWrapper extends VmBaseResponseWrapper<VmSmIncident> {
    public VmIncidentWrapper(Integer count, Integer start, Integer totalCount, String[] messages, String resourceName, Integer returnCode, VmModelWrapper<VmSmIncident>[] content) {
        super(count, start, totalCount, messages, resourceName, returnCode, content);
    }
}
