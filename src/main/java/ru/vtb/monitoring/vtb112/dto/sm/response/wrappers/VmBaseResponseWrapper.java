package ru.vtb.monitoring.vtb112.dto.sm.response.wrappers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.monitoring.vtb112.dto.sm.response.VmBaseModel;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmBaseResponseWrapper<T extends VmBaseModel> {
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
    private List<VmModelWrapper<T>> content;
}