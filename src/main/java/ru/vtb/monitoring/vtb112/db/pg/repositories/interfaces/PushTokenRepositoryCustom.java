package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.pg.models.PushTokens;

@Repository
public interface PushTokenRepositoryCustom {
    Boolean pushToken(PushTokens pushTokens);
}
