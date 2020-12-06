package ru.vtb.monitoring.vtb112.services.api.interfaces;

import ru.vtb.monitoring.vtb112.dto.api.response.VmSystemResponse;

import java.util.List;

public interface SystemsService {
    List<VmSystemResponse> get(int page, int limit);

    List<String> getTop10Unavailabilities();
}
