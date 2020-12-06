package ru.vtb.monitoring.vtb112.dto.sm.response.wrappers;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmBaseModel;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmSmUnavailability;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmModelWrapper<T extends VmBaseModel> {

    @JsonAlias("VtbChange")
    private VmSmChange vmSmChange;

    @JsonAlias("VtbIncident")
    private VmSmIncident vmSmIncident;

    @JsonAlias("vtbUnavailability")
    private VmSmUnavailability vmSmUnavailability;

    @SuppressWarnings("unchecked")
    public T getModel() {
        if (vmSmChange != null) {
            return (T) vmSmChange;
        } else if (vmSmIncident != null) {
            return (T) vmSmIncident;
        } else if (vmSmUnavailability != null) {
            return (T) vmSmUnavailability;
        } else {
            return null;
        }
    }
}
