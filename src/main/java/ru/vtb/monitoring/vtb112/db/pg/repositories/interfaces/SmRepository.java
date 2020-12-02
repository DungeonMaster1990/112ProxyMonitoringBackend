package ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces;

import java.util.List;

public interface SmRepository <T> {
    void putModels(List<T> models);
}
