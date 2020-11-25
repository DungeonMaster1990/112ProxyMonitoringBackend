package Monitoring.Monitoring.services.api.impl;

import Monitoring.Monitoring.db.models.PushTokens;
import Monitoring.Monitoring.db.repositories.interfaces.PushTokenRepository;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmPushTokenRequest;
import Monitoring.Monitoring.mappers.PushTokenMapper;
import Monitoring.Monitoring.services.api.interfaces.PushTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PushTokenServiceImpl implements PushTokenService {
    private PushTokenRepository pushTokenRepository;
    private PushTokenMapper pushTokenMapper;

    @Autowired
    public PushTokenServiceImpl(PushTokenRepository pushTokenRepository){
        this.pushTokenRepository = pushTokenRepository;
    }

    @Override
    public Boolean pushToken(VmPushTokenRequest pushTokenRequest) {
        PushTokens pushToken = pushTokenMapper.mapToPushTokens(pushTokenRequest);
        return pushTokenRepository.pushToken(pushToken);
    }
}
