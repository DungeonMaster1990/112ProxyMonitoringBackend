package ru.vtb.monitoring.vtb112.dto.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VmPushTokenRequest {

    @NotNull(message = "token cannot be null")
    @ApiModelProperty(example = "'123'")
    private String token;
    @NotNull(message = "installId cannot be null")
    @ApiModelProperty(example = "'1'")
    private String installId;
    @NotNull(message = "platform cannot be null")
    @ApiModelProperty(example = "ios")
    private String platform;
}
