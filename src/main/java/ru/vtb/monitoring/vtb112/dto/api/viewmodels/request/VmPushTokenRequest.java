package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VmPushTokenRequest {
    private String token;
    private String installId;
    private String platform;
}
