package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmBaseModel;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmChange;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmIncident;
import ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.VmSmUnavailability;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmModelWrapper<T extends VmBaseModel> {

    @JsonAlias("VtbChange")
    private VmSmChange vmSmChange;

    @JsonAlias("VtbIncident")
    private VmSmIncident vmSmIncident;

    @JsonAlias("vtbUnavailability")
    private VmSmUnavailability vmSmUnavailability;

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
