package Monitoring.Monitoring.dto.viewmodels.request;

import Monitoring.Monitoring.dto.viewmodels.enums.BlWorkType;
import org.springframework.lang.Nullable;

import java.util.Calendar;

public class VmEventRequest {
    private Calendar startDate;
    private Calendar finishDate;
    @Nullable
    private BlWorkType type;

    public VmEventRequest(Calendar startDate, Calendar finishDate, @Nullable BlWorkType type) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.type = type;
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

    @Nullable
    public BlWorkType getType() {
        return type;
    }

    public void setType(@Nullable BlWorkType type) {
        this.type = type;
    }
}
