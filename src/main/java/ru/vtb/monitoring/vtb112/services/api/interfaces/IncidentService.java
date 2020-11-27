package ru.vtb.monitoring.vtb112.services.api.interfaces;

import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.*;

import java.util.List;

public interface IncidentService {

    List<VmAccidentResponse> getAccidents(VmAccidentsRequest request);

    VmNewAccidentResponse getNewAccident();

    VmAccidentInfoResponse getAccidentInfo(Integer id);

    VmAccidentWorkersResponse getWorkers(Integer id);

    List<VmAccidentDescriptionResponse> getAccidentDescriptions(Integer id);
}
