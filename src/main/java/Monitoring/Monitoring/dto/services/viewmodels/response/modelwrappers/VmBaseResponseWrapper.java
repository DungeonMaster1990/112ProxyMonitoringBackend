package Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class VmBaseResponseWrapper<T> {
    @JsonAlias("@count")
    private Integer count;
    @JsonAlias("@start")
    private Integer start;
    @JsonAlias("@start")
    private Integer totalCount;
    @JsonAlias("Messages")
    private String[] messages;
    @JsonAlias("ResourceName")
    private String resourceName;
    @JsonAlias("ReturnCode")
    private Integer returnCode;
    @JsonAlias("content")
    private VmModelWrapper<T>[] content;

    public VmBaseResponseWrapper(Integer count, Integer start, Integer totalCount, String[] messages, String resourceName, Integer returnCode, VmModelWrapper<T>[] content) {
        this.count = count;
        this.start = start;
        this.totalCount = totalCount;
        this.messages = messages;
        this.resourceName = resourceName;
        this.returnCode = returnCode;
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public VmModelWrapper<T>[] getContent() {
        return content;
    }

    public void setContent(VmModelWrapper<T>[] content) {
        this.content = content;
    }
}