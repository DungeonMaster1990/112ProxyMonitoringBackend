package ru.vtb.monitoring.vtb112.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Qualifier("smRestTemplate")
    public RestTemplate smRestTemplate(AppConfig appConfig, RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication(appConfig.getSmLogin(), appConfig.getSmPassword()).build();
    }
}
