package Monitoring.Monitoring.dto.services.viewmodels.response.submodels;

public class VmContent {
    private VmVtbChange reChange;

    public VmContent(VmVtbChange reChange) {
        this.reChange = reChange;
    }

    public VmVtbChange getReChange() {
        return reChange;
    }

    public void setReChange(VmVtbChange reChange) {
        this.reChange = reChange;
    }
}
