package ru.vtb.monitoring.vtb112.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.vtb.monitoring.vtb112.db.pg"
)
public class AppConfig implements WebMvcConfigurer {

    @Value("${sm.baseurl}")
    private String baseSmUrl;
    @Value("${sm.methods.incident}")
    private String smIncidentMethod;
    @Value("${sm.methods.unavailability}")
    private String smUnavailabilityMethod;
    @Value("${sm.methods.changes}")
    private String smChangesMethod;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${spring.jpa.properties.hibernate.jdbc.time_zone}")
    private String hibernateTimeZone;

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
    @Value("#{'${api.categories}'.split(',')}")
    private List<String> supportedCategories;

    public String getSmIncidentUrl() {
        return this.baseSmUrl + this.smIncidentMethod;
    }

    public String getSmUnavailabilityUrl() {
        return this.baseSmUrl + this.smUnavailabilityMethod;
    }

    public String getSmUserLoginPass() {
        return String.format("%s:%s", smLogin, smPassword);
    }

    public String getSmChangesUrl() {
        return this.baseSmUrl + this.smChangesMethod;
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ru.vtb.monitoring.vtb112.db.pg");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.jdbc.time_zone", hibernateTimeZone);
        em.setJpaPropertyMap(properties);
        return em;
    }

}
