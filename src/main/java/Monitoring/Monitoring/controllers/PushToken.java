package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.dto.viewmodels.request.VmPushTokenRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushToken {
    @PostMapping("/api/v1.0/pushToken/update")
    public boolean pushTokenUpdate(VmPushTokenRequest vmPushTokenRequest){
        return true;
    }
}
