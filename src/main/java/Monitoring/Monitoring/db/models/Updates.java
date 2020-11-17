package Monitoring.Monitoring.db.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="updates", schema = "monitoring")
public class Updates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "service_name", unique = true, nullable = false)
    private String serviceName;

    @Column(name = "update_time", unique = false, nullable = false)
    private ZonedDateTime updateTime;

    public Updates(Integer id, String serviceName, ZonedDateTime updateTime) {
        this.id = id;
        this.serviceName = serviceName;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
