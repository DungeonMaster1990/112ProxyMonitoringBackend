package Monitoring.Monitoring.dto.viewmodels.response;

import Monitoring.Monitoring.dto.viewmodels.submodels.VmManager;
import Monitoring.Monitoring.dto.viewmodels.submodels.VmWorker;

public class VmAccidentWorkersResponse {
    private VmManager manager;
    private VmWorker[] workers;

    public VmAccidentWorkersResponse(VmManager manager, VmWorker[] workers) {
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
