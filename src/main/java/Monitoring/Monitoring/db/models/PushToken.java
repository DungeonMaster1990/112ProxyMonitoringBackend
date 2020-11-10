package Monitoring.Monitoring.db.models;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "pushToken",
       schema = "monitoring")
@Data
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
}
