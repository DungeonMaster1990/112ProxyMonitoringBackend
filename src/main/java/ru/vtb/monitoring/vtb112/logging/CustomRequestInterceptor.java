package ru.vtb.monitoring.vtb112.logging;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.Instant;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Pattern API_PATTERN = Pattern.compile("^/api/v\\d+\\.\\d+(/.+)");

    private static final String START_TIME_ATTRIBUTE = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        request.setAttribute(START_TIME_ATTRIBUTE, Instant.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) {
        var matcher = API_PATTERN.matcher(request.getServletPath());
        if (!matcher.find()) {
            return;
        }
        var startTime =  (Instant) request.getAttribute(START_TIME_ATTRIBUTE);
        var principal = Optional.ofNullable(request.getUserPrincipal())
                .map(Principal::getName)
                .orElse("");
        String endpoint = matcher.group(1);
        var event = LogEvent.getEventByPath(endpoint);
        var logEntry = new LogEntry.LogEntryBuilder()
                .username(principal)
                .startTime(startTime)
                .duration(Instant.now().toEpochMilli() - startTime.toEpochMilli())
                .clientIpAddress(request.getRemoteAddr())
                .userAgent(request.getHeader("User-Agent"))
                .endpoint(endpoint)
                .eventCode(event.getLogCode())
                .description(event.getDescription())
                .result(response.getStatus())
                .build();

        log.info("{}", logEntry);
    }
}
