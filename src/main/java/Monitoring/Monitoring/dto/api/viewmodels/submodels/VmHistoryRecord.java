package Monitoring.Monitoring.dto.api.viewmodels.submodels;

import java.time.ZonedDateTime;

public class VmHistoryRecord {
    private String name;
    private ZonedDateTime finishDate;
    private String role;
    private String description;

    public VmHistoryRecord(String name, ZonedDateTime finishDate, String role, String description) {
        this.name = name;
        this.finishDate = finishDate;
        this.role = role;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(ZonedDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
