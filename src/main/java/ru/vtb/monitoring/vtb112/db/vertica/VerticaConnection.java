package ru.vtb.monitoring.vtb112.db.vertica;

import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class VerticaConnection implements AutoCloseable {
    private Connection connection;
    private final String verticaUrl;
    private final Properties verticaUserPass;

    public VerticaConnection(AppConfig appConfig) {
        this.verticaUrl = appConfig.getVerticaUrl();
        this.verticaUserPass = appConfig.getVerticaUserPass();
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(verticaUrl, verticaUserPass);
        }
        return this.connection;
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
}
