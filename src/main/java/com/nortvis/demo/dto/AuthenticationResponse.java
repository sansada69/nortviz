package com.nortvis.demo.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String JWT_TOKEN;

    public AuthenticationResponse(String jwt) {
        this.JWT_TOKEN = jwt;
    }

    public String getJWT_TOKEN() {
        return JWT_TOKEN;
    }
}
