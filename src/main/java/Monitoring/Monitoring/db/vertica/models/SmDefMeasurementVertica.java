package Monitoring.Monitoring.db.vertica.models;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SmDefMeasurementVertica {
    private Integer sessionId;

    private Integer measurementId;

    private Integer schedId;

    private Integer categoryId;

    @Nullable
    private Integer monitorId;

    private Integer targetId;

    private String msname;

    @Nullable
    private String msid;

    @Nullable
    private String userRemark;

    @Nullable
    private String connectionData;

    @Nullable
    private Integer dmConnectionId;

    private Integer active;

    @Nullable
    private String ciId;

    @Nullable
    private String etiId;

    @Nullable
    private String integrationName;

    @Nullable
    private String profileId;

    private ZonedDateTime modifiedDate;

    private ZonedDateTime creationDate;

    private boolean isDeleted;
}
