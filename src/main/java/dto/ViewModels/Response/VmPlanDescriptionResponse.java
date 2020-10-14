package dto.ViewModels.Response;

import org.springframework.beans.factory.annotation.Autowired;

public class VmPlanDescriptionResponse {
    private String name;
    private String value;

    @Autowired
    public VmPlanDescriptionResponse(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
