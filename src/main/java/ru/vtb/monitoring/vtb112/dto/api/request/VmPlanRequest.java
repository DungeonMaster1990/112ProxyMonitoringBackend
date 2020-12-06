package ru.vtb.monitoring.vtb112.dto.api.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VmPlanRequest extends VmPageRequestBase {

    @ApiModelProperty(value = "Ключевое слово для поиска", example = "Авария")
    private String keyword;
    @NotNull(message = "planSectionID cannot be null")
    @Min(1)
    @Max(3)
    @JsonAlias("planSectionID")
    private Integer planSectionId;

}
