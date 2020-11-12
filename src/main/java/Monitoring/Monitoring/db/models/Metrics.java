package Monitoring.Monitoring.db.models;

import javax.persistence.*;

@Entity
@Table(name="metrics", schema = "monitoring")
public class Metrics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "measurement_id", unique = false, nullable = false)
    private String measurementId;

    @Column(name = "msname", unique = false, nullable = false)
    private String msname;

    public Metrics(int id, String measurementId, String msname) {
        this.id = id;
        this.measurementId = measurementId;
        this.msname = msname;
    }

    public Metrics(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public String getMsname() {
        return msname;
    }

    public void setMsname(String msname) {
        this.msname = msname;
    }
}
