package com.aresky.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.aresky.authservice.jwt.JwtUtils;

import reactor.core.publisher.Mono;

@Component
public class WebSecurityContext implements ServerSecurityContextRepository {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(auth -> auth.startsWith(jwtUtils.getPrefix()))
                .flatMap(auth -> {
                    String token = auth.substring(jwtUtils.getPrefix().length()).trim();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(null, token);
                    return authenticationManager.authenticate(authentication)
                            .map(SecurityContextImpl::new);
                });
    }
}
