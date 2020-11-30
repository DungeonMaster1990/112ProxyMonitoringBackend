package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmErrorResponse {
    private String errorMessage;
    private String requestCode;
}
