package dto.viewmodels.request;

import org.springframework.lang.Nullable;

public class VmSystemsRequest extends VmPageRequestBase {
    @Nullable
    private Boolean onlyMine;
    private String keyword;

    public VmSystemsRequest(@Nullable Boolean onlyMine, String keyword, int limit, int page) {
        super(limit, page);
        this.onlyMine = onlyMine;
        this.keyword = keyword;
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
