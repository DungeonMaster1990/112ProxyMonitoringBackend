package Monitoring.Monitoring.db.models;

import javax.persistence.*;

@Entity
@Table(name="pushToken", schema = "monitoring")
public class PushToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token", unique = false, nullable = false)
    private String token;

    @Column(name = "installId", unique = false, nullable = false)
    private String installId;

    @Column(name = "platform", unique = false, nullable = false)
    private String platform;

    public PushToken(){}

    public PushToken(int id, String token, String installId, String platform) {
        this.id = id;
        this.token = token;
        this.installId = installId;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
