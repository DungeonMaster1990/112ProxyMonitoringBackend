package Monitoring.Monitoring.dto.viewmodels.request;

import java.time.ZonedDateTime;

public class VmMetricInfoRequest extends VmPageRequestBase {
    private String id;
    private ZonedDateTime startDate;
    private ZonedDateTime finishDate;

    public VmMetricInfoRequest(String id, ZonedDateTime startDate, ZonedDateTime finishDate, int limit, int page) {
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

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(ZonedDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
