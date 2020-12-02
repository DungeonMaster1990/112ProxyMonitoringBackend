package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.mainmodels.submodels;

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
public class VmSmChangeMiddle {
    @JsonAlias("SchedOutageStartAt")
    private ZonedDateTime schedOutageStartAt;
    @JsonAlias("SchedOutageEndAt")
    private ZonedDateTime schedOutageEndAt;
    @JsonAlias("Assets")
    private String[] assets;
    @JsonAlias("AffectedServices")
    private String[] affectedServices;
    @JsonAlias("DownStartAt")
    private ZonedDateTime downStartAt;
    @JsonAlias("DownEndAt")
    private ZonedDateTime downEndAt;
}
