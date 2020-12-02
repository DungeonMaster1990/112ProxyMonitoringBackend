package ru.vtb.monitoring.vtb112.db.pg.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="updates", schema = "monitoring")
public class Updates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_name", unique = true, nullable = false)
    private String serviceName;

    @Column(name = "update_time", unique = false, nullable = false)
    private ZonedDateTime updateTime;
}
