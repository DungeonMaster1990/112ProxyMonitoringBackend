package dto.viewmodels.submodels;

public class VmManager {
    private String name;
    private String avatar;
    private String role;

    public VmManager(String name, String avatar, String role) {
        this.name = name;
        this.avatar = avatar;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
