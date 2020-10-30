package Monitoring.Monitoring.services.api.interfaces;

import Monitoring.Monitoring.dto.viewmodels.request.VmAccidentsRequest;
import Monitoring.Monitoring.dto.viewmodels.response.VmAccidentResponse;
import Monitoring.Monitoring.dto.viewmodels.response.VmAccidentsResponse;

import java.util.ArrayList;
import java.util.List;

public interface AccidentsService {
    ArrayList<VmAccidentsResponse> getAllAccidents();
    void putAccidents(List<VmAccidentsRequest> vtbAccidents);
    VmAccidentResponse getAccident(int id);
}
