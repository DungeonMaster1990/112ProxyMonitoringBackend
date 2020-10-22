package Monitoring.Monitoring.dto.viewmodels.submodels;

import java.util.Calendar;

public class VmHistoryRecord {
    private String name;
    private Calendar finishDate;
    private String role;
    private String description;

    public VmHistoryRecord(String name, Calendar finishDate, String role, String description) {
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

    public Calendar getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Calendar finishDate) {
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
