package ru.vtb.monitoring.vtb112.dto.services.viewmodels.response.modelwrappers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class VmBaseResponseWrapper<T> {
    @JsonAlias("@count")
    private Integer count;
    @JsonAlias("@start")
    private Integer start;
    @JsonAlias("@totalcount")
    private Integer totalCount;
    @JsonAlias("Messages")
    private String[] messages;
    @JsonAlias("ResourceName")
    private String resourceName;
    @JsonAlias("ReturnCode")
    private Integer returnCode;
    @JsonAlias("content")
    private VmModelWrapper<T>[] content;
}