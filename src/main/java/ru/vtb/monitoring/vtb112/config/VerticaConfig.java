package ru.vtb.monitoring.vtb112.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.vtb.monitoring.vtb112.db.vertica",
        entityManagerFactoryRef = "verticaEntityManager",
        transactionManagerRef = "verticaTransactionManager"
)
public class VerticaConfig {

    @Value("${vertica.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${vertica.hibernate.connection.pool-size}")
    private String poolSize;

    @Bean
    @ConfigurationProperties("spring.vertica")
    public DataSourceProperties verticaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("verticaDataSource")
    public DataSource verticaDataSource() {
        return verticaDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("verticaEntityManager")
    public LocalContainerEntityManagerFactoryBean verticaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(verticaDataSource());
        em.setPackagesToScan("ru.vtb.monitoring.vtb112.db.vertica");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", hibernateDialect);
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
