package ru.vtb.monitoring.vtb112.dto.sm.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmUnavailability implements VmBaseModel {
    @JsonAlias("BeginAt")
    private ZonedDateTime beginAt;
    @JsonAlias("CreatedAt")
    private ZonedDateTime createdAt;
    @JsonAlias("CreatedById")
    private String createdById;
    @JsonAlias("Duration")
    private String duration;
    @JsonAlias("EndAt")
    private ZonedDateTime endAt;
    @JsonAlias("FaultId")
    private String faultId;
    @JsonAlias("ServiceId")
    private String serviceId;
    @JsonAlias("ServiceName")
    private String serviceName;
    @JsonAlias("Type")
    private String type;
    @JsonAlias("UpdatedAt")
    private ZonedDateTime updatedAt;
    @JsonAlias("UpdatedById")
    private int updatedById;
}
