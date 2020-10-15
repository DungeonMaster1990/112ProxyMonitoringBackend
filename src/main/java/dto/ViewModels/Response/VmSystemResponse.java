package dto.ViewModels.Response;

import org.springframework.beans.factory.annotation.Autowired;

public class VmSystemResponse {
    private String  id;
    private String name;
    private Boolean mine;
    private int criticalAccidents;
    private int majorAccidents;
    private int minorAccidents;

    @Autowired
    public VmSystemResponse(String id, String name, Boolean mine, int criticalAccidents, int majorAccidents, int minorAccidents) {
        this.id = id;
        this.name = name;
        this.mine = mine;
        this.criticalAccidents = criticalAccidents;
        this.majorAccidents = majorAccidents;
        this.minorAccidents = minorAccidents;
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

    public Boolean getMine() {
        return mine;
    }

    public void setMine(Boolean mine) {
        this.mine = mine;
    }

    public int getCriticalAccidents() {
        return criticalAccidents;
    }

    public void setCriticalAccidents(int criticalAccidents) {
        this.criticalAccidents = criticalAccidents;
    }

    public int getMajorAccidents() {
        return majorAccidents;
    }

    public void setMajorAccidents(int majorAccidents) {
        this.majorAccidents = majorAccidents;
    }

    public int getMinorAccidents() {
        return minorAccidents;
    }

    public void setMinorAccidents(int minorAccidents) {
        this.minorAccidents = minorAccidents;
    }
}
