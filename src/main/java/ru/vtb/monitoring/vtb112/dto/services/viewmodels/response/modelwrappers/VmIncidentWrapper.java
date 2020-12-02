package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmIncidentWrapper extends VmBaseResponseWrapper<VmSmIncident> {
    public VmIncidentWrapper(Integer count, Integer start, Integer totalCount, String[] messages, String resourceName, Integer returnCode, VmModelWrapper<VmSmIncident>[] content) {
        super(count, start, totalCount, messages, resourceName, returnCode, content);
    }
    public VmIncidentWrapper(){
        super();
    }
}
