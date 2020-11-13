package Monitoring.Monitoring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Value("${sm.baseurl}")
    private String baseSmUrl;
    @Value("${sm.methods.incident}")
    private String smIncidentMethod;
    @Value("${sm.methods.unavailability}")
    private String SmUnavailabilityMethod;
    @Value("${sm.methods.changes}")
    private String SmChangesMethod;

    private String smUnavailabilityMethod;
    @Value("${api.timeout}")
    private int timeout;
    @Value("${api.deep.days}")
    private long deepDays;
    @Value("${spring.verticaDatasource.url}")
    private String verticaUrl;
    @Value("${spring.verticaDatasource.password}")
    private String verticaPassword;
    @Value("${spring.verticaDatasource.username}")
    private String verticaUser;
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
        return deepDays;
    }

    public String getSmIncidentUrl(){
        return this.baseSmUrl + this.smIncidentMethod;
    }

    public String getSmUnavailabilityUrl(){
        return this.baseSmUrl + this.smUnavailabilityMethod;
    }


    public Properties getVerticaUserPass(){
        Properties verticaProps = new Properties();
        verticaProps.put("user", this.verticaUser);
        verticaProps.put("password", this.verticaPassword);
        verticaProps.put("LoginTimeout", "35");
        return verticaProps;
    }

    public String getVerticaUrl() {
        return verticaUrl;
    }
    public String getSmChangesUrl() { return this.baseSmUrl + this.SmChangesMethod; }
}
