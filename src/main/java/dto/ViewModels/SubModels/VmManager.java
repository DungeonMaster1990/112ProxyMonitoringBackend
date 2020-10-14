package dto.ViewModels.SubModels;

import org.springframework.beans.factory.annotation.Autowired;

public class VmManager {
    private string name;
    private string avatar;
    private string role;

    @Autowired
    public VmManager(string name, string avatar, string role) {
        this.name = name;
        this.avatar = avatar;
        this.role = role;
    }

    public string getName() {
        return name;
    }

    public void setName(string name) {
        this.name = name;
    }

    public string getAvatar() {
        return avatar;
    }

    public void setAvatar(string avatar) {
        this.avatar = avatar;
    }

    public string getRole() {
        return role;
    }

    public void setRole(string role) {
        this.role = role;
    }
}
