package ru.vtb.monitoring.vtb112.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPushTokenRequest;
import ru.vtb.monitoring.vtb112.services.api.interfaces.PushTokenService;

@RestController
@RequestMapping(value = PathConstants.PUSH_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
public class PushToken {

    private final PushTokenService pushTokenService;

    @Autowired
    public PushToken(PushTokenService pushTokenService) {
        this.pushTokenService = pushTokenService;
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean pushTokenUpdate(@RequestBody VmPushTokenRequest vmPushTokenRequest){
        return pushTokenService.pushToken(vmPushTokenRequest);
    }
}