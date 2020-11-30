package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.PushTokenRepositoryCustom;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPushTokenRequest;
import ru.vtb.monitoring.vtb112.services.api.interfaces.PushTokenService;

@RestController
@RequestMapping(value = PathConstants.PUSH_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PushToken {

    private PushTokenService pushTokenService;

    @Autowired
    public PushToken(PushTokenService pushTokenService) {
        this.pushTokenService = pushTokenService;
    }

    @PostMapping("/update")
    public boolean pushTokenUpdate(VmPushTokenRequest vmPushTokenRequest){
        return pushTokenService.pushToken(vmPushTokenRequest);
    }
}
