package ru.vtb.monitoring.vtb112.services.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPageRequestBase;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPlanRequest;

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

    public static void validatePlansRequest(VmPlanRequest request) {
        validatePageAndLimit(request);
        if (request.getPlanSectionID() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр planSectionID должен быть задан");
        }

    }

    public static void validatePageAndLimit(VmPageRequestBase request) {
        if (request.getPage() < 1 || request.getLimit() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметры page и limit не могут быть меньше 1");
        }
    }
}
