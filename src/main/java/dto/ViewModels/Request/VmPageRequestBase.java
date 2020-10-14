package dto.ViewModels.Request;

import org.springframework.beans.factory.annotation.Required;

public class VmPageRequestBase {
    private int limit;
    private int page;

    public VmPageRequestBase(int limit, int page) {
        this.limit = limit;
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
