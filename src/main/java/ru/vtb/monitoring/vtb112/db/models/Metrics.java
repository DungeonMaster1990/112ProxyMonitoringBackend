package ru.vtb.monitoring.vtb112.db.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "metrics",
       schema = "monitoring")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "measurement_id", nullable = false)
    private Integer measurementId;

    @Column(name = "msname", nullable = false)
    private String msname;

    @Column(name = "monitor_id", nullable = false)
    private Integer monitorId;

    @Column(name = "is_merged", nullable = false)
    private boolean isMerged;
}
