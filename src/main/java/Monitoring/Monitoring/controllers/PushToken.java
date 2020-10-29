package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.dto.viewmodels.request.VmPushTokenRequest;
import Monitoring.Monitoring.services.interfaces.PushTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushToken {
    private PushTokenService pushTokenService;

    @Autowired
    public PushToken(PushTokenService pushTokenService) {
        this.pushTokenService = pushTokenService;
    }

    @PostMapping("/api/v1.0/pushToken/update")
    public boolean pushTokenUpdate(VmPushTokenRequest vmPushTokenRequest){
        return true;
    }
}
