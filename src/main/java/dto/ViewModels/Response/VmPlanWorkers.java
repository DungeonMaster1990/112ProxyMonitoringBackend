package dto.ViewModels.Response;

import dto.ViewModels.SubModels.VmManager;
import dto.ViewModels.SubModels.VmWorker;
import org.springframework.beans.factory.annotation.Autowired;

public class VmPlanWorkers {
    private VmManager manager;
    private VmWorker[] workers;

    @Autowired
    public VmPlanWorkers(VmManager manager, VmWorker[] workers) {
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