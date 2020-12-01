package ru.vtb.monitoring.vtb112.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Properties;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${sm.baseurl}")
    private String baseSmUrl;
    @Value("${sm.methods.incident}")
    private String smIncidentMethod;
    @Value("${sm.methods.unavailability}")
    private String smUnavailabilityMethod;
    @Value("${sm.methods.changes}")
    private String smChangesMethod;

    @Getter
    @Value("${spring.verticaDatasource.url}")
    private String verticaUrl;
    @Value("${spring.verticaDatasource.password}")
    private String verticaPassword;
    @Value("${spring.verticaDatasource.username}")
    private String verticaUser;
    @Getter
    @Value("${notificationSender.url}")
    private String pusherUrl;
    @Getter
    @Value("${notificationSender.incidents.lastDaysToProcess}")
    private long lastDaysToProcess;
    @Value("${sm.login}")
    private String smLogin;
    @Value("${sm.password}")
    private String smPassword;
    @Getter
    @Value("${sm.port:}")
    private String smPort;
    @Getter
    @Value("${vertica.limit}")
    private Integer verticaLimit;
    @Getter
    @Value("${vertica.maxPages}")
    private Integer verticaMaxPages;
    @Getter
    @Value("#{'${api.categories}'.split(',')}")
    private List<String> supportedCategories;

    public String getSmIncidentUrl() {
        return this.baseSmUrl + this.smIncidentMethod;
    }

    public String getSmUnavailabilityUrl() {
        return this.baseSmUrl + this.smUnavailabilityMethod;
    }

    public Properties getVerticaUserPass() {
        Properties verticaProps = new Properties();
        verticaProps.put("user", this.verticaUser);
        verticaProps.put("password", this.verticaPassword);
        verticaProps.put("LoginTimeout", "35");
        return verticaProps;
    }

    public String getSmUserLoginPass() {
        return String.format("%s:%s", smLogin, smPassword);
    }

    public String getSmChangesUrl() {
        return this.baseSmUrl + this.smChangesMethod;
    }
}
