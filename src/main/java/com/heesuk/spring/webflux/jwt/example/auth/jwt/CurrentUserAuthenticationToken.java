package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.UUID;

/**
 * AbstractAuthenticationToken : Base class for Authentication objects.
 */
public class CurrentUserAuthenticationToken extends AbstractAuthenticationToken {
    private final String tokenType;
    private final UUID userId;
    private final DecodedJWT decodedJwtToken;

    @Override
    public Object getCredentials() {
        return this.decodedJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }

    public CurrentUserAuthenticationToken(String tokenType, UUID userId, DecodedJWT decodedJwtToken) {
        super(null);
        this.tokenType = tokenType;
        this.userId = userId;
        this.decodedJwtToken = decodedJwtToken;
        super.setAuthenticated(true);
    }

}