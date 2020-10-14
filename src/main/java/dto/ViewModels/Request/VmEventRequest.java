package dto.ViewModels.Request;

import dto.ViewModels.Request.Enums.BlWorkType;
import org.springframework.lang.Nullable;

import java.util.Calendar;

public class VmEventRequest {
    public Calendar startDate;
    public Calendar finishDate;
    @Nullable
    public BlWorkType type;
}
