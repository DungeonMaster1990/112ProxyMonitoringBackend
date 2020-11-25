package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.PushTokenRepositoryCustom;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPushTokenRequest;
import ru.vtb.monitoring.vtb112.services.api.interfaces.PushTokenService;

@RestController
public class PushToken {
    private PushTokenService pushTokenService;

    @Autowired
    public PushToken(PushTokenService pushTokenService) {
        this.pushTokenService = pushTokenService;
    }

    @PostMapping("/api/v1.0/pushToken/update")
    public boolean pushTokenUpdate(VmPushTokenRequest vmPushTokenRequest){
        return pushTokenService.pushToken(vmPushTokenRequest);
    }
}
