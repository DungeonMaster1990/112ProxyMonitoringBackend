package ru.vtb.monitoring.vtb112.infrastructure;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Vertica extends GenericContainer<Vertica> {

    private static final int VERTICA_HOST_PORT = 5433;
    private static final int VERTICA_CONTAINER_PORT = 5433;

    private static final String DEFAULT_DOCKER_IMAGE = "dataplatform/docker-vertica";

    public static final Vertica vertica;

    static {
        vertica = new Vertica(DEFAULT_DOCKER_IMAGE)
                .waitingFor(new LogMessageWaitStrategy()
                        .withRegEx(".*Vertica is now running.*\\s")
                        .withStartupTimeout(Duration.of(60, SECONDS)))
                .withReuse(true);
        vertica.start();
    }

    public Vertica() {
        this(DEFAULT_DOCKER_IMAGE);
    }

    public Vertica(String dockerImageName) {
        super(dockerImageName);
        this.addExposedPort(VERTICA_CONTAINER_PORT);
        this.addFixedExposedPort(VERTICA_HOST_PORT, VERTICA_CONTAINER_PORT);
    }

    @DynamicPropertySource
    static void verticaProperties(DynamicPropertyRegistry registry) {
        String url = "jdbc:vertica://"+vertica.getContainerIpAddress()+":"+VERTICA_HOST_PORT+"/docker";
        registry.add("spring.verticaDatasource.url", url::toString);
        registry.add("spring.verticaDatasource.username", "dbadmin"::toString);
    }
}
