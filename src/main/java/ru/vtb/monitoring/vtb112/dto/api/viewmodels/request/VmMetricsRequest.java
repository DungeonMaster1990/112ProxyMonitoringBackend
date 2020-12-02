package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class VmMetricsRequest extends VmPageRequestBase {
    @Nullable
    private Boolean onlyMine;
    private String keyword;

    public VmMetricsRequest(@Nullable Boolean onlyMine, String keyword, int limit, int page) {
        super(limit, page);
        this.onlyMine = onlyMine;
        this.keyword = keyword;
    }

    public VmMetricsRequest() {
        super(20, 0);
    }
}
