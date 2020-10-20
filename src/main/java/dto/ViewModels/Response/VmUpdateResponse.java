package dto.ViewModels.Response;

import org.springframework.beans.factory.annotation.Autowired;

public class VmUpdateResponse {
    private String result;

    public VmUpdateResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
