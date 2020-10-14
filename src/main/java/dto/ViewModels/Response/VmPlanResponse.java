package dto.ViewModels.Response;

import dto.ViewModels.Enums.BlAccidentStatusType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

public class VmPlanResponse {
    private String id;
    private String name;
    private String status;
    private BlAccidentStatusType statusType;
    private String description;
    private String[] affectedSystems;
    private Calendar startDate;

    @Autowired
    public VmPlanResponse(String id, String name, String status, BlAccidentStatusType statusType, String description, String[] affectedSystems, Calendar startDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.statusType = statusType;
        this.description = description;
        this.affectedSystems = affectedSystems;
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BlAccidentStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(BlAccidentStatusType statusType) {
        this.statusType = statusType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAffectedSystems() {
        return affectedSystems;
    }

    public void setAffectedSystems(String[] affectedSystems) {
        this.affectedSystems = affectedSystems;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
}
