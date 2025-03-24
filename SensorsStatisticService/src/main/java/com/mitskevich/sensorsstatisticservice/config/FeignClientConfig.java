package com.mitskevich.sensorsstatisticservice.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("sensors-client")
                .principal("sensor-statistics-client") // ✅ Должно совпадать с client-id
                .build();

            OAuth2AuthorizedClient client = authorizedClientManager.authorize(authorizeRequest);

            if (client == null) {
                throw new IllegalStateException("❌ Не удалось получить `access_token` для sensors-client");
            }

            String token = client.getAccessToken().getTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        };
    }
}