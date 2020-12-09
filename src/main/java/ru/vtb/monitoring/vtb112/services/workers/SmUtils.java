package ru.vtb.monitoring.vtb112.services.workers;

import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;

public class SmUtils {

    private SmUtils(){};

    public static String buildQuery(Instant updateTime) {
        return String.format("UpdatedAt>'%s'", updateTime);
    }


    /**
     * @param baseUrl         - url without parameters
     * @param smPort          - port of SM instance
     * @param updateTime      - time for seek new rows
     * @param pageNumber      - number of page, would be converted to start index seeking rows
     * @param countPerRequest - max count of rows for single request
     * @return built url
     */
    public static String buildUrl(String baseUrl,
                                  Integer smPort,
                                  Instant updateTime,
                                  int pageNumber,
                                  int countPerRequest) {
        if (pageNumber < 1 || countPerRequest < 1) {
            throw new IllegalArgumentException("startIndex or countPerRequest parameter should be more than 0");
        }
        int startIndex = pageNumber == 1
                ? pageNumber
                : countPerRequest * (pageNumber - 1) + 1;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        if (smPort != null) {
            builder.queryParam("serverPort", smPort);
        }
        builder.queryParam("view", "expand")
                .queryParam("query", buildQuery(updateTime))
                .queryParam("start", startIndex)
                .queryParam("count", countPerRequest);

        return builder.build(false).toUriString();
    }
}
