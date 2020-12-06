package ru.vtb.monitoring.vtb112.dto.api.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

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
    @ApiModelProperty(value = "Идентификатор секции плановых работ", example = "'1'")
    private Integer planSectionId;
    @Nullable
    @ApiModelProperty(value = "Дата начала плановой работы", example = "2020-06-26T18:00:00Z")
    private ZonedDateTime startDate;
    @Nullable
    @ApiModelProperty(value = "Дата окончания плановой работы", example = "2020-06-26T19:00:00Z")
    private ZonedDateTime finishDate;

}
