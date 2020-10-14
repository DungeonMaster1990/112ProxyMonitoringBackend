package dto.ViewModels.Request;

import java.util.Calendar;

public class VmMetricInfoRequest extends VmPageRequestBase {
    private String id;
    private Calendar startDate;
    private Calendar finishDate;

    public VmMetricInfoRequest(String id, Calendar startDate, Calendar finishDate, int limit, int page) {
        super(limit, page);
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Calendar finishDate) {
        this.finishDate = finishDate;
    }
}
