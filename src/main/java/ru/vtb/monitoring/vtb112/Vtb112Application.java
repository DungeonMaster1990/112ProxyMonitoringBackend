package ru.vtb.monitoring.vtb112;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Vtb112Application {
    public static void main(String[] args) {
        SpringApplication.run(Vtb112Application.class, args);
    }
}
