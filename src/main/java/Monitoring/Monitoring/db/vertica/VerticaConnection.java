package Monitoring.Monitoring.db.vertica;

import Monitoring.Monitoring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VerticaConnection {
    private Connection connection;

    @Autowired
    public VerticaConnection(AppConfig appConfig) throws SQLException {
        this.connection = DriverManager.getConnection(appConfig.getVerticaUrl(), appConfig.getVerticaUserPass());
    }

    public Connection getConnection() {
        return connection;
    }
}
