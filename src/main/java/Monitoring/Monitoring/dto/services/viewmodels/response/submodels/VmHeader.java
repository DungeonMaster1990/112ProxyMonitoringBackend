package Monitoring.Monitoring.dto.services.viewmodels.response.submodels;

public class VmHeader {
    private String Number;

    public VmHeader(String number) {
        Number = number;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
