package Monitoring.Monitoring.dto.api.viewmodels.response;

public class VmUpdateResponse {
    private String result;

    public VmUpdateResponse(String result) {
        this.result = result;
    }

    public VmUpdateResponse(){}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
