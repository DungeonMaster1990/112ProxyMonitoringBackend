package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmModelWrapper<T> {
    @JsonAlias({"vtbUnavailability", "VtbChange", "VtbIncident"})
    private T model;
}
