package ru.vtb.monitoring.vtb112.dto.api.viewmodels.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.enums.BlWorkType;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmEventResponse {
    private String id;
    private ZonedDateTime date;
    private BlWorkType type;

    @JsonGetter
    public BlWorkType getType() {
        return type;
    }
}
