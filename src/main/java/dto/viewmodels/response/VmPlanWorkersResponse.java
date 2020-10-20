package dto.viewmodels.response;

import dto.viewmodels.submodels.VmManager;
import dto.viewmodels.submodels.VmWorker;

public class VmPlanWorkersResponse {
    private VmManager manager;
    private VmWorker[] workers;

    public VmPlanWorkersResponse(VmManager manager, VmWorker[] workers) {
        this.manager = manager;
        this.workers = workers;
    }

    public VmManager getManager() {
        return manager;
    }

    public void setManager(VmManager manager) {
        this.manager = manager;
    }

    public VmWorker[] getWorkers() {
        return workers;
    }

    public void setWorkers(VmWorker[] workers) {
        this.workers = workers;
    }
}


