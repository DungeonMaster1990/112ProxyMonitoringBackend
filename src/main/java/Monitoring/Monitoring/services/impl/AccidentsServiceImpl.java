package Monitoring.Monitoring.services.impl;

import Monitoring.Monitoring.db.models.Accidents;
import Monitoring.Monitoring.db.repositories.interfaces.AccidentsRepository;
import Monitoring.Monitoring.dto.viewmodels.request.VmAccidentsRequest;
import Monitoring.Monitoring.dto.viewmodels.response.VmAccidentResponse;
import Monitoring.Monitoring.dto.viewmodels.response.VmAccidentsResponse;
import Monitoring.Monitoring.services.interfaces.AccidentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentsServiceImpl implements AccidentsService {
    private AccidentsRepository accidentsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccidentsServiceImpl(AccidentsRepository accidentsRepository, ModelMapper modelMapper) {
        this.accidentsRepository = accidentsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<VmAccidentsResponse> getAllAccidents() {
        var accidents = accidentsRepository.getAllAccidents();
        var vmAccidents = new ArrayList<VmAccidentsResponse>();
        modelMapper.map(accidents, vmAccidents);
        return vmAccidents;
    }

    @Override
    public void putAccidents(List<VmAccidentsRequest> vtbAccidents) {
        var accidents = new ArrayList<Accidents>();
        modelMapper.map(vtbAccidents,accidents);
        accidentsRepository.putAccidents(accidents);
    }

    @Override
    public VmAccidentResponse getAccident(int id) {
        VmAccidentResponse vmAccidentsResponse = new VmAccidentResponse();
        var accident = accidentsRepository.getAccident(id);
        modelMapper.map(accident, vmAccidentsResponse);
        return vmAccidentsResponse;
    }
}
