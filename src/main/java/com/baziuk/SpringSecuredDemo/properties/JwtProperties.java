package com.baziuk.SpringSecuredDemo.properties;

import lombok.Getter;

import java.time.Duration;

@Getter
public class JwtProperties {
    private final String secret = "5rd5e5w5s5x5c5vv6b7hh8u87jh88q9u0o9i8n8h8yg8v86vg66h";
    private final Duration lifetime = Duration.ofSeconds(1800);
}
