package ru.vtb.monitoring.vtb112.db.models;

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
@Table(name = "pushtokens", schema = "monitoring")
public class PushTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "token", unique = false, nullable = false)
    private String token;

    @Column(name = "install_id", unique = false, nullable = false)
    private String installId;

    @Column(name = "platform", unique = false, nullable = false)
    private String platform;

    @Column(name = "update_token_date", unique = false, nullable = false)
    private ZonedDateTime updateTokenDate;
}
