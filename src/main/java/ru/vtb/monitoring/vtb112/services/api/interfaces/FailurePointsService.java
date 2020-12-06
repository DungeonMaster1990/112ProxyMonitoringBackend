package ru.vtb.monitoring.vtb112.services.api.interfaces;

import java.util.List;

public interface FailurePointsService {

    List<String> getTop10FailurePoints();
}
