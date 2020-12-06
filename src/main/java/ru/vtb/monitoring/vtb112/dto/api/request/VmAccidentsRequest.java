package ru.vtb.monitoring.vtb112.dto.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VmAccidentsRequest extends VmPageRequestBase {

    private ZonedDateTime startDate;
    @ApiModelProperty(example = "['Все аварии']")
    private List<String> affectedSystems;
    @ApiModelProperty(example = "['М-Банк']")
    private List<String> failurePoints;
    @Nullable
    @ApiModelProperty(example = "'1'")
    private String systemId;
    @ApiModelProperty(example = "IM-")
    private String keyword;
}
