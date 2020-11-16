package Monitoring.Monitoring.dto.api.viewmodels.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class VmAccidentsRequest extends VmPageRequestBase {
    private ZonedDateTime startDate;
    private List<String> affectedSystems;
    private String[] failurePoints;
    @Nullable
    private Long systemId;
    @Nullable
    private int planTypeId;
    private String keyword;
}
