package ru.vtb.monitoring.vtb112.infrastructure;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Vertica extends GenericContainer<Vertica> {

    private static final int VERTICA_HOST_PORT = 5433;
    private static final String VERTICA_DB_USER = "dbadmin";

    private static final String DEFAULT_DOCKER_IMAGE = "dataplatform/docker-vertica";

    private static Vertica vertica;

    public static Vertica getInstance() {
        if (vertica == null) {
            vertica = new Vertica(DEFAULT_DOCKER_IMAGE)
                    .waitingFor(new LogMessageWaitStrategy()
                            .withRegEx(".*Vertica is now running.*\\s")
                            .withStartupTimeout(Duration.of(60, SECONDS)))
                    .withReuse(true);
            vertica.start();
        }
        return vertica;
    }

    public Vertica(String dockerImageName) {
        super(dockerImageName);
    }

    @Override
    public void start() {
        super.start();
        Integer exposePort = vertica.getMappedPort(VERTICA_HOST_PORT);
        String url = "jdbc:vertica://"+Vertica.vertica.getContainerIpAddress()+":"+exposePort+"/docker";
        System.setProperty("VERTICA_URL", url);
        System.setProperty("VERTICA_USER", VERTICA_DB_USER);
    }
}
