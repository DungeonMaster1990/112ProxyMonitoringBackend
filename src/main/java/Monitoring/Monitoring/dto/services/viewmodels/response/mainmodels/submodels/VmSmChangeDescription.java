package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.submodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
    @JsonAlias("VtbRiskDescription")
    private String[] vtbRiskDescription;
}
