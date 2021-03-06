package ru.vtb.monitoring.vtb112.dto.sm.response.submodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmChangeDescription {
    @JsonAlias("Description")
    private String[] description;
    @JsonAlias("Justification")
    private String[] justification;
    @JsonAlias("AcceptanceComments")
    private String[] acceptanceComments;
    @JsonAlias("Plan")
    private String[] plan;
    @JsonAlias("RiskDescription")
    private String[] riskDescription;
}
