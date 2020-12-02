package ru.vtb.monitoring.vtb112.db.pg.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "metrics", schema = "monitoring")
public class Metrics {
    @Id
    @GenericGenerator(
            name = "metricsIdGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "monitoring.metrics_id_seq")
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
