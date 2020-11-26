package ru.vtb.monitoring.vtb112.db.vertica.models;

import lombok.*;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmRawdataMeasVertica {
    private Integer sessionId;

    private ZonedDateTime timeStamp;

    private Integer measurementId;

    private Float measValue;

    @Nullable
    private Integer statusId;

    private String errMsg;

    private Integer rawMonitorId;

    private Integer rawTargetId;

    private Integer rawConnectionId;

    private Integer rawCategoryId;

    @Nullable
    private Integer rawThresholdQuality;

    @Nullable
    private ZonedDateTime dbdate;
}
