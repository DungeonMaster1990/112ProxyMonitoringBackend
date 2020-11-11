package Monitoring.Monitoring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${sm.baseurl}")
    private String baseSmUrl;
    @Value("${sm.methods.incident}")
    private String smIncidentMethod;
    @Value("${sm.methods.unavailability}")
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

    public long getDeepDays() {
        return deepDays;
    }

    public String getSmIncidentUrl(){
        return this.baseSmUrl + this.smIncidentMethod;
    }

    public String getSmUnavailabilityUrl(){
        return this.baseSmUrl + this.smUnavailabilityMethod;
    }

    public String getVerticaPassword() {
        return verticaPassword;
    }

    public void setVerticaPassword(String verticaPassword) {
        this.verticaPassword = verticaPassword;
    }

    public String getVerticaUser() {
        return verticaUser;
    }

    public void setVerticaUser(String verticaUser) {
        this.verticaUser = verticaUser;
    }
}
