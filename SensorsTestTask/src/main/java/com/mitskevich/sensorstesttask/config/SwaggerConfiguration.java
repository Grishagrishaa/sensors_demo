package com.mitskevich.sensorstesttask.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.OAUTH2;

@Configuration
public class SwaggerConfiguration {

    private static final String OAUTH_SCHEME_NAME = "keyCloak";

    @Value("${app.keycloak.url}")
    private String authServerUrl;
    @Value("${app.keycloak.realm}")
    private String realm;

    @Bean
    public OpenAPI openAPI() {
        Components components = new Components().addSecuritySchemes(OAUTH_SCHEME_NAME, createOAuthScheme());
        SecurityRequirement securityItem = new SecurityRequirement().addList(OAUTH_SCHEME_NAME);
        Info projectInfo = new Info().title("Sensors Management Service");

        return new OpenAPI()
            .components(components)
            .addSecurityItem(securityItem)
            .info(projectInfo);
    }

    private SecurityScheme createOAuthScheme() {
        OAuthFlows flows = new OAuthFlows().authorizationCode(createAuthorizationCodeFlow());

        return new SecurityScheme()
            .type(OAUTH2)
            .flows(flows);
    }

    private OAuthFlow createAuthorizationCodeFlow() {
        String authorizationUrl = String.format("%s/realms/%s/protocol/openid-connect/auth", authServerUrl, realm);

        return new OAuthFlow()
            .authorizationUrl(authorizationUrl)
            .tokenUrl(authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token");
    }

}