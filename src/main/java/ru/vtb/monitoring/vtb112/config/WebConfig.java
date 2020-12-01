package ru.vtb.monitoring.vtb112.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.vtb.monitoring.vtb112.logging.CustomRequestInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CustomRequestInterceptor customRequestInterceptor;

    public WebConfig(CustomRequestInterceptor customRequestInterceptor) {
        this.customRequestInterceptor = customRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customRequestInterceptor)
                .addPathPatterns("/**/api/**/");
    }
}
