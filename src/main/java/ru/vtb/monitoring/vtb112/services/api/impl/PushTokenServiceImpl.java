package ru.vtb.monitoring.vtb112.services.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.db.pg.models.PushTokens;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.PushTokenRepository;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPushTokenRequest;
import ru.vtb.monitoring.vtb112.mappers.PushTokenMapper;
import ru.vtb.monitoring.vtb112.services.api.interfaces.PushTokenService;

@Service
public class PushTokenServiceImpl implements PushTokenService {
    private PushTokenRepository pushTokenRepository;
    private PushTokenMapper pushTokenMapper;

    @Autowired
    public PushTokenServiceImpl(PushTokenRepository pushTokenRepository, PushTokenMapper pushTokenMapper){
        this.pushTokenRepository = pushTokenRepository;
        this.pushTokenMapper = pushTokenMapper;
    }

    @Override
    public Boolean pushToken(VmPushTokenRequest pushTokenRequest) {
        PushTokens pushToken = pushTokenMapper.mapToPushTokens(pushTokenRequest);
        return pushTokenRepository.pushToken(pushToken);
    }
}
