package Monitoring.Monitoring.logging;

import lombok.Getter;

import java.util.Arrays;

public enum LogEvent {

    ACCIDENTS              ("/accidents", "accidents.get", "Readonly operation"),
    ACCIDENTS_NEW          ("/accidents/new", "accidents.getNewAccident", "Readonly operation"),
    ACCIDENTS_INFO         ("/accidents/info", "accidents.getAccidentInfo", "Readonly operation"),
    ACCIDENTS_WORKERS      ("/accidents/workers", "accidents.getWorkers", "Readonly operation"),
    ACCIDENTS_HISTORY      ("/accidents/history", "accidents.getHistory", "Readonly operation"),
    ACCIDENTS_DESCRIPTIONS ("/accidents/descriptions", "accidents.getAccidentDescriptions", "Readonly operation"),
    EVENTS                 ("/events", "events.get", "Readonly operation"),
    FAILURE                ("/failurePoints", "failure.get", "Readonly operation"),
    METRICS                ("/metrics", "metrics.get", "Readonly operation"),
    METRICS_INFO           ("/metrics/info", "metrics.info", "Readonly operation"),
    PLANS                  ("/plans", "plans.get", "Readonly operation"),
    PLANS_SECTIONS         ("/plans/sections", "plans.getPlanSections", "Readonly operation"),
    PLANS_INFO             ("/plans/info", "plans.getPlanInfo", "Readonly operation"),
    PLANS_WORKERS          ("/plans/workers", "plans.getPlanWorkers", "Readonly operation"),
    PLANS_HISTORY          ("/plans/history", "plans.getPlanHistory", "Readonly operation"),
    PLANS_DESCRIPTIONS     ("/plans/descriptions", "plans.getPlanDescriptions", "Readonly operation"),
    SYSTEMS                ("/systems", "systems.get", "Readonly operation"),
    SYSTEMS_AFFECTED       ("/affected", "systems.getAffectedSystem", "Readonly operation"),

    UNKNOWN                ("", "", "");

    private final String eventPath;
    @Getter
    private final String logCode;
    @Getter
    private final String description;

    LogEvent(String eventPath, String logCode, String description) {
        this.eventPath = eventPath;
        this.logCode = logCode;
        this.description = description;
    }

    public static LogEvent getEventByPath(String path) {
        return Arrays.stream(LogEvent.values())
                .filter(s -> s.eventPath.equals(path))
                .findFirst()
                .orElse(UNKNOWN);
    }

}
