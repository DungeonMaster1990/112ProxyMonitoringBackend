package ru.vtb.monitoring.vtb112.services.api.interfaces;

import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPushTokenRequest;

public interface PushTokenService {
    Boolean pushToken(VmPushTokenRequest pushTokenRequest);
}
