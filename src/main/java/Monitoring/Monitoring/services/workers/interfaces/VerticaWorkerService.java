package Monitoring.Monitoring.services.workers.interfaces;

import java.sql.SQLException;

public interface VerticaWorkerService {
    void takeSmDefMeasurementVertica() throws SQLException;
    void takeSmRawdataMeasVertica() throws SQLException;
}
