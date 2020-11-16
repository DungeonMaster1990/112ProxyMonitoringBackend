package Monitoring.Monitoring.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmPageRequestBase {
    private int limit;
    private int page;
}
