package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VmModelWrapper<T> {

    @JsonAlias({"vtbUnavailability", "VtbChange", "VtbIncident"})
    private T model;
}
