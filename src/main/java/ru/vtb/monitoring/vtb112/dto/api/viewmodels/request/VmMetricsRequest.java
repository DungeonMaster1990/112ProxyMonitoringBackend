package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import org.springframework.lang.Nullable;


public class VmMetricsRequest extends VmPageRequestBase {
    @Nullable
    private Boolean onlyMine;
    private String  keyword;

    public VmMetricsRequest(@Nullable Boolean onlyMine, String keyword, int limit, int page) {
        super(limit, page);
        this.onlyMine = onlyMine;
        this.keyword = keyword;
    }

    public VmMetricsRequest() {
        super(20, 0);
    }

    @Nullable
    public Boolean getOnlyMine() {
        return onlyMine;
    }

    public void setOnlyMine(@Nullable Boolean onlyMine) {
        this.onlyMine = onlyMine;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
