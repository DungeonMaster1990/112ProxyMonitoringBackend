package Monitoring.Monitoring.db.vertica;

import Monitoring.Monitoring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class VerticaConnection {
    private Connection connection;
    private AppConfig appConfig;

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
