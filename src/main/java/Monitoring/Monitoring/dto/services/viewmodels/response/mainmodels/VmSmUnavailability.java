package Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmSmUnavailability {
    @JsonAlias("FaultId")
    private String faultId;
    @JsonAlias("BeginAt")
    private ZonedDateTime beginAt;
    @JsonAlias("EndAt")
    private ZonedDateTime endAt;
    @JsonAlias("Duration")
    private String duration;
    @JsonAlias("ServiceName")
    private String serviceName;
    @JsonAlias("Type")
    private String type;
    @JsonAlias("ServiceId")
    private String serviceId;
    @JsonAlias("CreatedAt")
    private ZonedDateTime createdAt;
    @JsonAlias("CreatedById")
    private String createdById;
    @JsonAlias("UpdatedAt")
    private ZonedDateTime updatedAt;
    @JsonAlias("UpdatedById")
    private int updatedById;

    public VmSmUnavailability(String faultId, ZonedDateTime beginAt, ZonedDateTime endAt, String duration, String serviceName, String type, String serviceId, ZonedDateTime createdAt, String createdById, ZonedDateTime updatedAt, int updatedById) {
        this.faultId = faultId;
        this.beginAt = beginAt;
        this.endAt = endAt;
        this.duration = duration;
        this.serviceName = serviceName;
        this.type = type;
        this.serviceId = serviceId;
        this.createdAt = createdAt;
        this.createdById = createdById;
        this.updatedAt = updatedAt;
        this.updatedById = updatedById;
    }
}
