package ru.vtb.monitoring.vtb112.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.vtb.monitoring.vtb112.vertica",
        entityManagerFactoryRef = "verticaEntityManager",
        transactionManagerRef = "verticaTransactionManager"
)
public class VerticaConfig {

    @Value("${spring.verticaDatasource.url}")
    private String verticaUrl;
    @Value("${spring.verticaDatasource.password}")
    private String verticaPassword;
    @Value("${spring.verticaDatasource.username}")
    private String verticaUser;
    @Value("${spring.verticaDatasource.driver}")
    private String verticaDriver;
    @Value("${vertica.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${spring.jpa.properties.hibernate.jdbc.time_zone}")
    private String hibernateTimeZone;
    @Value("${vertica.hibernate.connection.pool_size}")
    private String poolSize;


    @Bean("verticaDataSource")
    public DataSource verticaDataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(verticaDriver);
        source.setUrl(verticaUrl);
        source.setUsername(verticaUser);
        source.setPassword(verticaPassword);
        return source;
    }

    @Bean("verticaEntityManager")
    public LocalContainerEntityManagerFactoryBean verticaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(verticaDataSource());
        em.setPackagesToScan("ru.vtb.monitoring.vtb112.vertica");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.jdbc.time_zone", hibernateTimeZone);
        properties.put("hibernate.connection.pool_size", poolSize);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager verticaTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                verticaEntityManager().getObject());
        return transactionManager;
    }

}
