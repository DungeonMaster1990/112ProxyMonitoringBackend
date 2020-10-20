package dto.viewmodels.submodels;

import dto.viewmodels.enums.BlWorkerStatus;

public class VmWorker {
    private String name;
    private BlWorkerStatus status;
    private String role;

    public VmWorker(String name, BlWorkerStatus status, String role) {
        this.name = name;
        this.status = status;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlWorkerStatus getStatus() {
        return status;
    }

    public void setStatus(BlWorkerStatus status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
