package ru.vtb.monitoring.vtb112.infrastructure;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers(disabledWithoutDocker = true)
public class PostgreSQL {

    static final PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:12-alpine")
                .withDatabaseName("iapp")
                .withUsername("iapp")
                .withPassword("iapp")
                .withReuse(true);
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @DynamicPropertySource
    static void testMigrationsProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.liquibase.contexts", "prod,test"::toString);
    }
}
