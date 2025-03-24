package com.mitskevich.sensorstesttask.config;

import org.springframework.boot.test.web.reactive.server.WebTestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Configuration
public class WebTestClientConfig {

    public static final String SESSION_ID = "245eb4ee-9ec2-4667-aa79-eb1e6ab6a0c2";

    @Bean
    public WebTestClientBuilderCustomizer webTestClientBuilderCustomizer() {
        return (builder) -> builder.responseTimeout(Duration.of(50, SECONDS));
    }

}

