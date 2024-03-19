package com.aresky.authservice.controller;

import com.aresky.authservice.constants.CompleteNotification;

import com.aresky.authservice.constants.KafkaTopic;
import com.aresky.authservice.event.KafkaProducerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.authservice.dto.AuthResponse;
import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.dto.UpdatePasswordForm;
import com.aresky.authservice.entity.ERole;
import com.aresky.authservice.exception.AuthException;
import com.aresky.authservice.service.IAuthService;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private KafkaProducerEvent producerEvent;

    @GetMapping("/home")
    public Mono<String> home() {
        return Mono.just("Welcome to auth service");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Mono<ResponseEntity<Page<AuthResponse>>> getAll(Pageable pageable) {
        return authService.findAll(pageable)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/validate")
    public Mono<ResponseEntity<?>> validateAuthAccount(@RequestParam long authId){
        return producerEvent
                .sendMessage(new KafkaProducerEvent.CustomMessage(KafkaTopic.AUTH_VALIDATION_REQUEST, String.valueOf(authId)))
                .then(Mono.just(ResponseEntity.ok("success")));
    };

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginForm form) {
        return authService.handleLogin(form)
                .flatMap(res -> Mono.just(ResponseEntity.ok(res)));
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signup(@RequestBody SignupForm form) {
        return authService.handleSignup(form)
                .flatMap(res -> {
                    if (res) {
                        return Mono.just(ResponseEntity.ok(CompleteNotification.SIGNUP_SUCCESS));
                    }

                    return Mono
                            .error(new AuthException("Error: Username already exists!", HttpStatus.BAD_REQUEST, "404"));
                });
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','EMPLOYEE')")
    @PatchMapping("/update-password")
    public Mono<ResponseEntity<String>> updatePassword(
            @RequestBody UpdatePasswordForm form,
            Authentication authentication) {
        String username = authentication.getName();
        return authService.handleUpdatePassword(form, username)
                .flatMap(isSuccess -> Mono.just(ResponseEntity.ok(CompleteNotification.UPDATE_PASSWORD_SUCCESS)));
    }

    // @PostMapping("/forgot-password")
    // public ResponseEntity<?> forgotPassword(@RequestParam String email) throws
    // MessagingException {
    // // mailService.sendForgotPasswordEmail(email);
    // return ResponseEntity.ok("success");
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        authService.delete(id);
        return ResponseEntity.ok("delete success");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/lock-account/{id}")
    public Mono<ResponseEntity<?>> lockAccount(@PathVariable Integer id) {
        return authService.lock(id).then(Mono.just(ResponseEntity.ok("success")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/activate-account/{id}")
    public Mono<ResponseEntity<?>> activeAccount(@PathVariable Integer id) {
        return authService.activate(id).then(Mono.just(ResponseEntity.ok("success")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/upgrade-role")
    public Mono<ResponseEntity<?>> upgradeRole(
            @RequestParam(name = "authId") long authId,
            @RequestParam(name = "role") ERole role) {
        return authService.updateRole(authId, role).then(Mono.just(ResponseEntity.ok("success")));
    }

}
