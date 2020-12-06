package ru.vtb.monitoring.vtb112.dto.api.submodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmManager {
    @ApiModelProperty(example = "Иванов И.И.")
    private String name;
    @ApiModelProperty(example = "https://www.nj.com/resizer/h8MrN0-Nw5dB5FOmMVGMmfVKFJo=/450x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg")
    private String avatar;
    @ApiModelProperty(example = "Начальник управления УАБД ДРИ")
    private String role;
}
