package Monitoring.Monitoring.services.workers.impl;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.Incidents;
import Monitoring.Monitoring.db.models.Unavailabilities;
import Monitoring.Monitoring.db.repositories.interfaces.IncidentsRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UnavailabilityRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.VmIncidentResponse;
import Monitoring.Monitoring.dto.services.viewmodels.response.VmUnavailabilityResponse;
import Monitoring.Monitoring.services.workers.interfaces.SmIncidentWorker;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SmUnavalabilityWorkerImpl {

}
