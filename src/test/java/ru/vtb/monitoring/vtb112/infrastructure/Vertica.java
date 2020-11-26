package ru.vtb.monitoring.vtb112.infrastructure;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Vertica extends GenericContainer<Vertica> {

    private static final int VERTICA_HOST_PORT = 5433;

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

    public Vertica(String dockerImageName) {
        super(dockerImageName);
    }

    public static Integer getVerticaPort() {
        return vertica.getMappedPort(VERTICA_HOST_PORT);
    }

    public static void setVerticaProperties(DynamicPropertyRegistry registry) {
        Integer exposePort = Vertica.getVerticaPort();
        String url = "jdbc:vertica://"+Vertica.vertica.getContainerIpAddress()+":"+exposePort+"/docker";
        registry.add("spring.verticaDatasource.url", url::toString);
        registry.add("spring.verticaDatasource.username", "dbadmin"::toString);
    }
}
