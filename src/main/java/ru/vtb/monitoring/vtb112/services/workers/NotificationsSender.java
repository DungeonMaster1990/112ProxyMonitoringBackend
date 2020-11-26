package ru.vtb.monitoring.vtb112.services.workers;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.models.Incident;
import ru.vtb.monitoring.vtb112.db.models.PushTokens;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.repositories.interfaces.PushTokenRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@Slf4j
public class NotificationsSender {
    @Autowired
    AppConfig appConfig;

    @Autowired
    private IncidentRepository vtbIncidentsRepository;

    @Autowired
    private PushTokenRepository pushTokenRepository;

    private List<String> supportedPriorities;

    public NotificationsSender(){
        supportedPriorities = Arrays.asList("1","2");
    }

    @Scheduled(fixedRateString = "${notificationsender.scheduler.fixedrate}")
    public void sendPushNotifications() {
        List<Incident> incidents = vtbIncidentsRepository.getTimeFilteredNonSentVtbIncidents(appConfig.getLastDaysToProcess());
        if (isEmpty(incidents)) {
            log.debug("Не обнаружено новых инцидентов для отправки.");
            return;
        }
        //log.debug("Обнаружено {} новых инцидентов для отправки.", incidents.size());
        RestTemplate restTemplate = new RestTemplate();
        try {
            // TODO отправлять все аварии в одном вызове?
            incidents.stream()
                    .filter(incident -> supportedPriorities.contains(incident.getPriority()))
                    .forEach(i -> sendNotificationsForIncident(restTemplate, i));

            var ids = incidents.stream().map(Incident::getId).collect(toSet());
            vtbIncidentsRepository.markAsNotificationSent(ids);
        }
        catch (Exception e) {
            log.error("Ошибка при передаче инцидента на сервис отправки уведомлений.", e);
        }
    }

    private void sendNotificationsForIncident(RestTemplate restTemplate, Incident i) {
        Map<String, Object> request = Map.of(
                "accident", buildAccidentInfo(i),
                "targets", buildTargets(pushTokenRepository.findAll()));

        ResponseEntity<Void> result = restTemplate.postForEntity(appConfig.getPusherUrl(), request, Void.class);
    }

    @NotNull
    private Map<String, Serializable> buildAccidentInfo(Incident i) {
        return Map.of(
                "id", i.getId(),
                "header", "Авария " + i.getId(),
                "description", "Обнаружена новая авария, подключитесь к устранению.",
                "name", "Инцидент " + i.getId());
    }

    @NotNull
    private List<Map<String, String>> buildTargets(List<PushTokens> allTokens) {
        return allTokens
                .stream()
                .map(t -> Map.of("token", t.getToken(), "osName", t.getPlatform()))
                .collect(toList());
    }
}
