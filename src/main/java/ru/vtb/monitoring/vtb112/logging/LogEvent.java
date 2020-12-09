package ru.vtb.monitoring.vtb112.logging;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum LogEvent {
// @formatter:off
    ACCIDENTS              ("/accidents",               "accidents.get",                     LogEvent.OPERATION_TYPE),
    ACCIDENTS_NEW          ("/accidents/new",           "accidents.getNewAccident",          LogEvent.OPERATION_TYPE),
    ACCIDENTS_INFO         ("/accidents/info",          "accidents.getAccidentInfo",         LogEvent.OPERATION_TYPE),
    ACCIDENTS_WORKERS      ("/accidents/workers",       "accidents.getWorkers",              LogEvent.OPERATION_TYPE),
    ACCIDENTS_HISTORY      ("/accidents/history",       "accidents.getHistory",              LogEvent.OPERATION_TYPE),
    ACCIDENTS_DESCRIPTIONS ("/accidents/descriptions",  "accidents.getAccidentDescriptions", LogEvent.OPERATION_TYPE),
    EVENTS                 ("/events",                  "events.get",                        LogEvent.OPERATION_TYPE),
    FAILURE                ("/failurePoints",           "failure.get",                       LogEvent.OPERATION_TYPE),
    METRICS                ("/metrics",                 "metrics.get",                       LogEvent.OPERATION_TYPE),
    METRICS_INFO           ("/metrics/info",            "metrics.info",                      LogEvent.OPERATION_TYPE),
    PLANS                  ("/plans",                   "plans.get",                         LogEvent.OPERATION_TYPE),
    PLANS_SECTIONS         ("/plans/sections",          "plans.getPlanSections",             LogEvent.OPERATION_TYPE),
    PLANS_INFO             ("/plans/info",              "plans.getPlanInfo",                 LogEvent.OPERATION_TYPE),
    PLANS_WORKERS          ("/plans/workers",           "plans.getPlanWorkers",              LogEvent.OPERATION_TYPE),
    PLANS_HISTORY          ("/plans/history",           "plans.getPlanHistory",              LogEvent.OPERATION_TYPE),
    PLANS_DESCRIPTIONS     ("/plans/descriptions",      "plans.getPlanDescriptions",         LogEvent.OPERATION_TYPE),
    SYSTEMS                ("/systems",                 "systems.get",                       LogEvent.OPERATION_TYPE),
    SYSTEMS_AFFECTED       ("/systems/affected",        "systems.getAffectedSystem",         LogEvent.OPERATION_TYPE),

    UNKNOWN                ("",                         "",                         "");
// @formatter:on

    private static final String OPERATION_TYPE = "Readonly operation";
    private static final Map<String, LogEvent> LOOKUP = Arrays.stream(LogEvent.values())
            .collect(Collectors.toUnmodifiableMap(LogEvent::getEventPath, logEvent -> logEvent));

    @Getter
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
        if (path != null && path.endsWith("/")) path = path.substring(0, path.length() - 1);
        return LOOKUP.getOrDefault(path, UNKNOWN);
    }

}
