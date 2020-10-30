package Monitoring.Monitoring.dto.api.viewmodels.request;

import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

public class VmPlanSectionRequest {
    @Nullable
    private ZonedDateTime startDate;
    @Nullable
    private ZonedDateTime finishDate;

    public VmPlanSectionRequest(@Nullable ZonedDateTime startDate, @Nullable ZonedDateTime finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    @Nullable
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nullable ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    @Nullable
    public ZonedDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(@Nullable ZonedDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
