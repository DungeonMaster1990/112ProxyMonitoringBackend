package ru.vtb.monitoring.vtb112.dto.api.viewmodels.submodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmManager {
    private String name;
    private String avatar;
    private String role;
}
