package ru.vtb.monitoring.vtb112.controllers;

public final class PathConstants {

    public static final String API = "/api";
    public static final String VERSION = "1.0";
    public static final String API_VERSION = API + "/v" + VERSION;
    public static final String ACCIDENTS = API_VERSION + "/accidents";
    public static final String EVENTS = API_VERSION + "/events";
    public static final String FAILURE_POINTS = API_VERSION + "/failurePoints";
    public static final String METRICS = API_VERSION + "/metrics";
    public static final String PUSH_TOKEN = API_VERSION + "/pushToken";
    public static final String PLANS = API_VERSION + "/plans";
    public static final String SYSTEMS = API_VERSION + "/systems";

    private PathConstants() {
    }
}
