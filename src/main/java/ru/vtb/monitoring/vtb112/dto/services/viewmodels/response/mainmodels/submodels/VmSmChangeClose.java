package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmChangeClose {
    @JsonAlias("ClosingComments")
    private String[] closingComments;
}
