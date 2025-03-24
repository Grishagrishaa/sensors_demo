package com.mitskevich.syberrytesttask.config.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ERoles {
    ADMIN("admin"), USER("user");

    private final String name;
    
}
