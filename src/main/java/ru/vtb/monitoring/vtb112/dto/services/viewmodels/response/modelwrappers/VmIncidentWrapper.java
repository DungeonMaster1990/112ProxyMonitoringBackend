package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;

import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;

public class VmIncidentWrapper extends VmBaseResponseWrapper<VmSmIncident> {
    public VmIncidentWrapper(Integer count, Integer start, Integer totalCount, String[] messages, String resourceName, Integer returnCode, VmModelWrapper<VmSmIncident>[] content) {
        super(count, start, totalCount, messages, resourceName, returnCode, content);
    }
}
