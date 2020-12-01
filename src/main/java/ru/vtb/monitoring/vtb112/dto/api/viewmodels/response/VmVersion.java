package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.VersionStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmVersion {
    private String status;

    public VmVersion(VersionStatus status) {
        this.status = status.name().toLowerCase();
    }

}