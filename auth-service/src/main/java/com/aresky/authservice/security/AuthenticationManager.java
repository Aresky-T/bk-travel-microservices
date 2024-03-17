package com.aresky.authservice.security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.aresky.authservice.jwt.JwtUtils;
import com.aresky.authservice.service.IAuthService;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IAuthService authService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = String.valueOf(authentication.getCredentials());
        Boolean isValidToken = jwtUtils.isValidToken(token);

        return Mono.just(isValidToken)
                .flatMap(isValid -> {
                    if (!isValid) {
                        return Mono.empty();
                    }

                    String username = jwtUtils.getSubjectFromToken(token);
                    return authService.findByUsername(username)
                            .map(authResult -> {
                                String role = "ROLE_" + String.valueOf(authResult.getRole());
                                List<GrantedAuthority> authorities = Stream.of(role).map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList());

                                return new UsernamePasswordAuthenticationToken(username, token, authorities);
                            });
                });
    }

}
