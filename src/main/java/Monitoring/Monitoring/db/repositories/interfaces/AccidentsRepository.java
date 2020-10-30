package Monitoring.Monitoring.db.repositories.interfaces;

import Monitoring.Monitoring.db.models.Accidents;

import java.util.List;

public interface AccidentsRepository {
    List<Accidents> getAllAccidents();
    void putAccidents(List<Accidents> vtbAccidents);
    Accidents getAccident(int id);
}
