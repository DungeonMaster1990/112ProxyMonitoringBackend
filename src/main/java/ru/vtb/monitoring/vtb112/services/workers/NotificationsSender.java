package ru.vtb.monitoring.vtb112.services.workers;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vtb.monitoring.vtb112.config.AppConfig;
import ru.vtb.monitoring.vtb112.db.pg.models.Incident;
import ru.vtb.monitoring.vtb112.db.pg.models.PushTokens;
import ru.vtb.monitoring.vtb112.db.pg.models.Updates;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.IncidentRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.PushTokenRepository;
import ru.vtb.monitoring.vtb112.db.pg.repositories.interfaces.UpdatesRepository;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;

@ConditionalOnProperty(value = "notificationSender.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Component
@Slf4j
public class NotificationsSender {

    private final AppConfig appConfig;
    private final UpdatesRepository updatesRepository;
    private final IncidentRepository vtbIncidentsRepository;
    private final PushTokenRepository pushTokenRepository;
    private final RestTemplate restTemplate;

    private final List<String> supportedCategories;

    private ZonedDateTime startTime;

    public NotificationsSender(AppConfig appConfig,
                               UpdatesRepository updatesRepository,
                               IncidentRepository vtbIncidentsRepository,
                               PushTokenRepository pushTokenRepository,
                               RestTemplateBuilder restTemplateBuilder) {
        this.appConfig = appConfig;
        this.updatesRepository = updatesRepository;
        this.supportedCategories = appConfig.getSupportedCategories();
        this.vtbIncidentsRepository = vtbIncidentsRepository;
        this.pushTokenRepository = pushTokenRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    private ZonedDateTime getStartTime(){
        if (startTime == null)
            startTime = updatesRepository
                    .getUpdateEntityByServiceName("Incidents")
                    .getStartTime();

        return  startTime;
    }

    @Scheduled(fixedRateString = "${notificationSender.scheduler.fixedRate}")
    public void sendPushNotifications() {
        List<Incident> incidents = vtbIncidentsRepository.getTimeFilteredNonSentVtbIncidents(appConfig.getLastDaysToProcess());
        ZonedDateTime startTime = getStartTime();
        if (isEmpty(incidents)) {
            log.debug("Не обнаружено новых инцидентов для отправки.");
            return;
        }
        log.debug("Обнаружено {} новых инцидентов для отправки.", incidents.size());
        try {
            // TODO отправлять все аварии в одном вызове?
            incidents.stream()
                    .filter(incident -> supportedCategories.contains(incident.getPriority()))
                    .filter(incident-> incident.getCreatedAt().isAfter(startTime))
                    .forEach(this::sendNotificationsForIncident);
            var ids = incidents.stream().map(Incident::getId).collect(toSet());
            vtbIncidentsRepository.markAsNotificationSent(ids);
        } catch (Exception e) {
            log.error("Ошибка при передаче инцидента на сервис отправки уведомлений.", e);
        }
    }

    private void sendNotificationsForIncident(Incident i) {
        Map<String, Object> request = Map.of(
                "accident", buildAccidentInfo(i),
                "targets", buildTargets(pushTokenRepository.findAll()));
        restTemplate.postForEntity(appConfig.getPusherUrl(), request, Void.class);
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
