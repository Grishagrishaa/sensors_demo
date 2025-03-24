package com.mitskevich.sensorstesttask.config.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    @SneakyThrows
    @Profile({"dev", "prod"})
    public SecurityFilterChain filterChain(HttpSecurity http) {

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter());

        return http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                    .requestMatchers("/swagger-ui/*").permitAll()
                                    .requestMatchers("/v3/api-docs*/**").permitAll()
                                    .requestMatchers(GET).hasAnyRole(ERoles.USER.getName(), ERoles.ADMIN.getName())
                                    .requestMatchers(POST).hasRole(ERoles.ADMIN.getName())
                                    .requestMatchers(PUT).hasRole(ERoles.ADMIN.getName())
                                    .requestMatchers(DELETE).hasRole(ERoles.ADMIN.getName())
                                    .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(authenticationConverter)))

                .build();
    }
}
