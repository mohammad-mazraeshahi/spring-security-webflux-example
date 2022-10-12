package com.heesuk.spring.webflux.jwt.example.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
public class CurrentUserAuthenticationBearer {

    public static Mono<Authentication> create(DecodedJWT decodedJWT) {
        String name;
        UUID userId;

        try {
            name = decodedJWT.getClaims().get("name").asString();
            userId = decodedJWT.getClaims().get("sub").as(UUID.class);
        } catch (Exception e) {
            log.error("json token parse error. invalid claims decoded JWT : " + decodedJWT.getClaims());
            return Mono.empty();
        }

        return Mono.justOrEmpty(new CurrentUserAuthenticationToken(name, userId, decodedJWT));
    }

}