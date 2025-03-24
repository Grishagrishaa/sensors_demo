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

        if(realmAccess == null || realmAccess.isEmpty()){
            return Collections.emptyList();
        }

        Collection<GrantedAuthority> roles = ((List<String>) realmAccess.get("roles")).stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return roles;
    }
}
