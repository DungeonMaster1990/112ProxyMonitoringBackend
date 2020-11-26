package ru.vtb.monitoring.vtb112.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.vtb.monitoring.vtb112.logging.CustomRequestInterceptor;

import java.util.Properties;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private CustomRequestInterceptor customRequestInterceptor;

    @Value("${sm.baseurl}")
    private String baseSmUrl;
    @Value("${sm.methods.incident}")
    private String smIncidentMethod;
    @Value("${sm.methods.unavailability}")
    private String smUnavailabilityMethod;
    @Value("${sm.methods.changes}")
    private String smChangesMethod;

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
    @Value("${pusher.url}")
    private String pusherUrl;
    @Value("${notificationsender.incidents.lastDaysToProcess}")
    private long lastDaysToProcess;
    @Value("${sm.login}")
    private String smLogin;
    @Value("${sm.password}")
    private String smPassword;
    @Value("${sm.port:}")
    private String smPort;

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

    public String getSmPort(){ return smPort; }

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

    public String getSmUserLoginPass(){
        return String.format("%s:%s", smLogin, smPassword);
    }

    public String getVerticaUrl() {
        return verticaUrl;
    }
    public String getSmChangesUrl() { return this.baseSmUrl + this.smChangesMethod; }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customRequestInterceptor)
                .addPathPatterns("/**/api/v1.0/**/");
    }
}
