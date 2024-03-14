package com.aresky.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.commonservice.jwt.JwtUtils;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    public Mono<String> home() {
        return Mono.just("Welcome to auth service");
    }

    @PostMapping("/login")
    private ResponseEntity<Mono<String>> login() {
        System.out.println(jwtUtils.getHeader());
        return ResponseEntity.ok(Mono.just(""));
    }

    @PostMapping("/signup")
    private ResponseEntity<Mono<String>> signup() {
        return ResponseEntity.ok(Mono.just(""));
    }

}
