package Monitoring.Monitoring.db.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "metrics",
       schema = "monitoring")
@Getter
@Setter
public class Metrics {
    @Id
    @GenericGenerator(
            name = "metricsIdGenerator",
            strategy = "sequence-identity",
            parameters = {
                    @Parameter(name = "sequence",
                               value = "monitoring.metrics_id_seq")
            }
    )
    @GeneratedValue(generator = "metricsIdGenerator")
    private Integer id;

    @Column(name = "measurement_id",
            nullable = false)
    private Integer measurementId;

    @Column(name = "msname",
            nullable = false)
    private String msname;

    @Column(name = "is_merged",
            nullable = false)
    private boolean isMerged;
}
