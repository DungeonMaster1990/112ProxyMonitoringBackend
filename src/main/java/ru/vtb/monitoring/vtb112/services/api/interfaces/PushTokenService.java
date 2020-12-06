package ru.vtb.monitoring.vtb112.services.api.interfaces;

import org.springframework.stereotype.Service;
import ru.vtb.monitoring.vtb112.dto.api.request.VmPushTokenRequest;

@Service
public interface PushTokenService {
    Boolean pushToken(VmPushTokenRequest pushTokenRequest);
}
