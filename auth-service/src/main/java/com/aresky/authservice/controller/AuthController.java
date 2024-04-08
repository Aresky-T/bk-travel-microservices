package com.aresky.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.service.auth.IAuthService;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestParam;

// @CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @GetMapping("/validate-token")
    public Mono<ResponseEntity<?>> validateAccessToken(@RequestParam String accessToken) {
        return authService.isValidAccessToken(accessToken).map(ResponseEntity::ok);
    };

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginForm form) {
        return authService.handleLogin(form)
                .flatMap(res -> Mono.just(ResponseEntity.ok(res)));
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signup(@RequestBody SignupForm form) {
        return authService.handleSignup(form).thenReturn(ResponseEntity.ok("success"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok("success");
    }

}
