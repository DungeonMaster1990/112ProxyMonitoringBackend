package Monitoring.Monitoring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${sm.baseurl}")
    private String BaseSmUrl;
    @Value("${sm.methods.incident}")
    private String SmIncidentMethod;
    @Value("${sm.methods.unavailability}")
    private String SmUnavailabilityMethod;
    @Value("${api.timeout}")
    private int Timeout;
    @Value("${api.deep.days}")
    private long DeepDays;
    @Value("${pusher.url}")
    private String pusherUrl;
    @Value("${notificationsender.incidents.lastDaysToProcess}")
    private long   lastDaysToProcess;

    public long getLastDaysToProcess() {
        return lastDaysToProcess;
    }

    public String getPusherUrl() {
        return pusherUrl;
    }

    public long getDeepDays() {
        return DeepDays;
    }

    public String getSmIncidentUrl(){
        return this.BaseSmUrl + this.SmIncidentMethod;
    }

    public String getSmUnavailabilityUrl(){
        return this.BaseSmUrl + this.SmUnavailabilityMethod;
    }


}
