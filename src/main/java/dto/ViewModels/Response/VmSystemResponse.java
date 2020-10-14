package dto.ViewModels.Response;

import org.springframework.beans.factory.annotation.Autowired;

public class VmSystemResponse {
    private String  id;
    private String name;
    private Boolean mine;
    private int criticalAccidents;
    private int majorAccedents;
    private int minorAccedents;

    @Autowired
    public VmSystemResponse(String id, String name, Boolean mine, int criticalAccidents, int majorAccedents, int minorAccedents) {
        this.id = id;
        this.name = name;
        this.mine = mine;
        this.criticalAccidents = criticalAccidents;
        this.majorAccedents = majorAccedents;
        this.minorAccedents = minorAccedents;
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

    public int getMajorAccedents() {
        return majorAccedents;
    }

    public void setMajorAccedents(int majorAccedents) {
        this.majorAccedents = majorAccedents;
    }

    public int getMinorAccedents() {
        return minorAccedents;
    }

    public void setMinorAccedents(int minorAccedents) {
        this.minorAccedents = minorAccedents;
    }
}
