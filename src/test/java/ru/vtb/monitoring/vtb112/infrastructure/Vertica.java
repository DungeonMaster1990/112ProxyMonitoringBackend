package ru.vtb.monitoring.vtb112.infrastructure;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
        System.setProperty("VERTICA_URL", getJdbcUrl());
        System.setProperty("VERTICA_USER", VERTICA_DB_USER);
        runSqlScript("vertica_test_data.sql");
    }

    public void runSqlScript(String sqlFileName) throws RuntimeException {
        String file = "src/test/resources/db/vendor/"+sqlFileName;
        try {
            String query = Files.readString(Paths.get(file), StandardCharsets.UTF_8);
            try (Connection connection = DriverManager.getConnection(getJdbcUrl(), VERTICA_DB_USER, "");
                 Statement stmt = connection.createStatement()) {
                stmt.execute(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getJdbcUrl() {
        Integer exposePort = vertica.getMappedPort(VERTICA_HOST_PORT);
        return  "jdbc:vertica://"+Vertica.vertica.getContainerIpAddress()+":"+exposePort+"/docker";
    }
}
