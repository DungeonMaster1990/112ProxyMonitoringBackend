package ru.vtb.monitoring.vtb112.logging;

import lombok.extern.slf4j.Slf4j;
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

    private final Pattern apiPattern = Pattern.compile("[^|(\\d+.+\\d).]*$");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", Instant.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {

        var matcher = apiPattern.matcher(request.getServletPath());
        if (!matcher.find()) {
            return;
        }
        var event = LogEvent.getEventByPath(matcher.group());
        var principal = Optional.ofNullable(request.getUserPrincipal())
                .map(Principal::getName)
                .orElse("");
        var startTime = (Instant) request.getAttribute("startTime");
        var duration = Instant.now().toEpochMilli() - startTime.toEpochMilli();
        var ipAdd = request.getRemoteAddr();
        var userAgent = request.getHeader("User-Agent");
        var eventLogCode = event.getLogCode();
        var description = event.getDescription();
        var result = response.getStatus();

        log.info("""
                 User name: {} 
                 Start time: {} 
                 Duration: {} 
                 Client ip address: {} 
                 User agent: {} 
                 Event code: {} 
                 Description: {}
                 Result: {}
                 """, principal, startTime, duration, ipAdd, userAgent, eventLogCode, description, result);
    }

}

