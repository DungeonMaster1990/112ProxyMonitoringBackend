package dto.ViewModels.Request;

import org.springframework.beans.factory.annotation.Autowired;

public class VmPlanRequest extends VmPageRequestBase {

    private String keyword;

    private int planSectionId;

    @Autowired
    public VmPlanRequest(String keyword, int planSectionId, int limit, int page) {
        super(limit, page);
        this.keyword = keyword;
        this.planSectionId = planSectionId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPlanSectionId() {
        return planSectionId;
    }

    public void setPlanSectionId(int planSectionId) {
        this.planSectionId = planSectionId;
    }
}
