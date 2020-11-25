package ru.vtb.monitoring.vtb112.db.repositories.implementations;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import ru.vtb.monitoring.vtb112.db.models.PushTokens;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.PushTokenRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.PushTokenRepositoryCustom;

@Log4j2
@Repository
public class PushTokenRepositoryImpl implements PushTokenRepositoryCustom {

    @Autowired
    @Lazy
    PushTokenRepository pushTokenRepository;

    @Override
    public Boolean pushToken(PushTokens pushTokens) {
        return null;
    }
}
