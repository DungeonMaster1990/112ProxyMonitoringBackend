package Monitoring.Monitoring.services.api.interfaces;

import Monitoring.Monitoring.dto.api.viewmodels.request.VmPushTokenRequest;

public interface PushTokenService {
    Boolean pushToken(VmPushTokenRequest pushTokenRequest);
}
