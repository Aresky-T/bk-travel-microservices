package com.aresky.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.entity.Auth;
import com.aresky.authservice.repository.AuthRepository;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImp implements IAuthService {
    @Autowired
    private AuthRepository authRepository;

    @Override
    public Mono<LoginResponse> handleLogin(LoginForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Void> handleSignup(SignupForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Boolean> isExistsAuth(Auth auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Boolean> isExistsByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Boolean> isExistsById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Boolean> isExistsByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Void> save(Auth auth) {
        // TODO Auto-generated method stub
        return null;
    }

}
