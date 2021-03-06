package ru.vtb.monitoring.vtb112.db.pg.repositories.implementations;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.PushTokens;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.PushTokenRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.PushTokenRepositoryCustom;
import ru.vtb.monitoring.vtb112.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Log4j2
@Repository
public class PushTokenRepositoryImpl implements PushTokenRepositoryCustom {

    @Autowired
    @Lazy
    private PushTokenRepository pushTokenRepository;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Boolean pushToken(PushTokens pushToken) {
        String pushTokenQry = """
                select t.id as id,
                t.token as token,
                t.install_id as install_id,
                t.platform as platform,
                t.update_token_date as update_token_date
                from monitoring.pushtokens t
                where install_id = :install_id and 
                      platform = :platform                     
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("install_id", pushToken.getInstallId())
                .addValue("platform", pushToken.getPlatform());

        List<PushTokens> existedPushTokens = namedParameterJdbcTemplate.query(
                pushTokenQry, sqlParameterSource, (rs, rowNum) -> toPushTokens(rs));
        if (!existedPushTokens.isEmpty()) {
            for (PushTokens tokenInfo : existedPushTokens) {
                tokenInfo.setToken(pushToken.getToken());
                tokenInfo.setUpdateTokenDate(ZonedDateTime.now());
                tokenInfo.setInstallId(pushToken.getInstallId());
                tokenInfo.setPlatform(pushToken.getPlatform());
            }
            pushTokenRepository.saveAll(existedPushTokens);
        } else {
            pushToken.setUpdateTokenDate(ZonedDateTime.now());
            pushTokenRepository.save(pushToken);
        }
        log.info("token was added: {}", pushToken);
        return true;
    }

    private PushTokens toPushTokens(ResultSet rs) throws SQLException {
        return PushTokens
                .builder()
                .id(rs.getInt("id"))
                .token(rs.getString("token"))
                .installId(rs.getString("install_id"))
                .updateTokenDate(DateUtil.dbDateToZonedDate(rs.getTimestamp("update_token_date"), "UTC"))
                .build();
    }
}
