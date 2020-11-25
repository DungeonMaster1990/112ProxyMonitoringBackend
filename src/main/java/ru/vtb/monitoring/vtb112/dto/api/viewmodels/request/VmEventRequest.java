package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

import org.springframework.lang.Nullable;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlWorkType;

import java.time.ZonedDateTime;

public class VmEventRequest {
    private ZonedDateTime startDate;
    private ZonedDateTime finishDate;
    @Nullable
    private BlWorkType type;

    public VmEventRequest(ZonedDateTime startDate, ZonedDateTime finishDate, @Nullable BlWorkType type) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.type = type;
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

    @Nullable
    public BlWorkType getType() {
        return type;
    }

    public void setType(@Nullable BlWorkType type) {
        this.type = type;
    }
}
