package Monitoring.Monitoring.db.repositories.interfaces;

import java.util.List;

public interface SmRepository <T> {
    void putModels(List<T> models);
}
