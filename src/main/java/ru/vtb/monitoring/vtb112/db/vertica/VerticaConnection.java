package ru.vtb.monitoring.vtb112.db.vertica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vtb.monitoring.vtb112.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class VerticaConnection {
    private Connection connection;
    private final AppConfig appConfig;

    @Autowired
    public VerticaConnection(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public Connection getConnection() throws SQLException {
        if(this.connection == null) {
            this.connection = DriverManager
                    .getConnection(appConfig.getVerticaUrl(), appConfig.getVerticaUserPass());

        }
        return this.connection;
    }
}
