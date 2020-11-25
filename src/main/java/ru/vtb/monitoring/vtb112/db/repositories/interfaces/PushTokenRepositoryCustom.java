package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.PushTokens;

@Repository
public interface PushTokenRepositoryCustom {
    Boolean pushToken(PushTokens pushTokens);
}
