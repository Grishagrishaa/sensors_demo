package com.mitskevich.sensorstesttask.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KCRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * Converts a Jwt object into a collection of GrantedAuthority objects representing the roles.
     *
     * @param jwt the Jwt object to convert
     * @return the collection of GrantedAuthority objects representing the roles
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        List<String> realmRoles = (List<String>) realmAccess.get("roles");

        Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        Map<String, Object> statisticClient = (Map<String, Object>) resourceAccess.get("sensor-statistics-client");
        List<String> statisticClientRoles = (List<String>) statisticClient.get("roles");

        realmRoles.addAll(statisticClientRoles);

        if(realmAccess == null || realmAccess.isEmpty()){
            return Collections.emptyList();
        }

        Collection<GrantedAuthority> roles = realmRoles.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return roles;
    }
}
