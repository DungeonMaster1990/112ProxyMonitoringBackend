package ru.vtb.monitoring.vtb112.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static int stringToInt(String param, String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException npe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр '" + param + "' должен быть числом. Получено: '" + value + '\'');
        }
    }
}
