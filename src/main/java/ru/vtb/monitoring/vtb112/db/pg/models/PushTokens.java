package ru.vtb.monitoring.vtb112.db.pg.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="pushtokens", schema = "monitoring")
public class PushTokens {
    @Id
    @GenericGenerator(
            name = "pushtokens_id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "monitoring.pushtokens_id_seq")
            }
    )
    @GeneratedValue(generator = "pushtokens_id_generator")
    private int id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "install_id", nullable = false)
    private String installId;

    @Column(name = "platform", nullable = false)
    private String platform;

    @Column(name = "update_token_date", nullable = false)
    private ZonedDateTime updateTokenDate;
}
