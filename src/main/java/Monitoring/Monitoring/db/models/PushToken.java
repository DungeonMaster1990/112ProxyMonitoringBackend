package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="pushToken", schema = "monitoring")
public class PushToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "token", unique = false, nullable = false)
    private String token;

    @Column(name = "installId", unique = false, nullable = false)
    private String installId;

    @Column(name = "platform", unique = false, nullable = false)
    private String platform;

    @Column(name = "update_token_date", unique = false, nullable = false)
    private ZonedDateTime updateTokenDate;

    public PushToken(int id, String token, String installId, String platform, ZonedDateTime updateTokenDate) {
        this.id = id;
        this.token = token;
        this.installId = installId;
        this.platform = platform;
        this.updateTokenDate = updateTokenDate;
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

    public ZonedDateTime getUpdateTokenDate() {
        return updateTokenDate;
    }

    public void setUpdateTokenDate(ZonedDateTime updateTokenDate) {
        this.updateTokenDate = updateTokenDate;
    }
}
