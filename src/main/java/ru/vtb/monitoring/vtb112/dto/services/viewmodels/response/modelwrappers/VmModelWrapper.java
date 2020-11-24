package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;


import com.fasterxml.jackson.annotation.JsonAlias;

public class VmModelWrapper <T> {

    @JsonAlias({"vtbUnavailability", "VtbChange", "VtbIncident"})
    private T model;

    public VmModelWrapper(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
