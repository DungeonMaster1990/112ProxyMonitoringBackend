package Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers;

import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmSmChange;

public class VmChangeWrapper extends VmBaseResponseWrapper<VmSmChange> {
    public VmChangeWrapper(Integer count, Integer start, Integer totalCount, String[] messages, String resourceName, Integer returnCode, VmModelWrapper<VmSmChange>[] content) {
        super(count, start, totalCount, messages, resourceName, returnCode, content);
    }
}
