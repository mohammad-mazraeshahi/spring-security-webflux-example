package com.heesuk.spring.webflux.jwt.example.controller;

import com.heesuk.spring.webflux.jwt.example.dto.HelloUser;
import com.heesuk.spring.webflux.jwt.example.util.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final AuthenticationFacade authenticationFacade;

    private Mono<Object> extractUserSeqIdFromJwtToken() {
        return authenticationFacade.getAuthentication()
                .map(Authentication::getPrincipal)
                .cast(Object.class);
    }

    @GetMapping("/v1/hello")
    public Mono<HelloUser> getHello() {
        return extractUserSeqIdFromJwtToken()
                .flatMap(userId -> Mono.just(new HelloUser(userId)));
    }
}
