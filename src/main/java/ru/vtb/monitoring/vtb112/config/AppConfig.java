package ru.vtb.monitoring.vtb112.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Value("${sm.baseurl}")
    private String baseSmUrl;
    @Value("${sm.methods.incident}")
    private String smIncidentMethod;
    @Value("${sm.methods.unavailability}")
    private String smUnavailabilityMethod;
    @Value("${sm.methods.changes}")
    private String smChangesMethod;

    @Getter
    @Value("${notificationSender.url}")
    private String pusherUrl;
    @Getter
    @Value("${notificationSender.incidents.lastDaysToProcess}")
    private long lastDaysToProcess;
    @Getter
    @Value("${sm.login}")
    private String smLogin;
    @Getter
    @Value("${sm.password}")
    private String smPassword;
    @Getter
    @Value("#{'${api.categories}'.split(',')}")
    private List<String> supportedCategories;

    public String getSmIncidentUrl() {
        return this.baseSmUrl + this.smIncidentMethod;
    }

    public String getSmUnavailabilityUrl() {
        return this.baseSmUrl + this.smUnavailabilityMethod;
    }

    public String getSmChangesUrl() {
        return this.baseSmUrl + this.smChangesMethod;
    }

}
