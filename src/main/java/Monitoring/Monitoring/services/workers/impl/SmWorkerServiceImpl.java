package Monitoring.Monitoring.services.workers.impl;

import Monitoring.Monitoring.services.workers.interfaces.SmWorkerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SmWorkerServiceImpl implements SmWorkerService {

    private static int i = 0;
    @Scheduled(fixedRate = 3000)
    public void takeDataFromSm(){
        
    }
}
