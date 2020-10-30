package Monitoring.Monitoring.dto.api.viewmodels.response;

public class VmPlanDescriptionResponse {
    private String name;
    private String value;

    public VmPlanDescriptionResponse(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public VmPlanDescriptionResponse(){}

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
