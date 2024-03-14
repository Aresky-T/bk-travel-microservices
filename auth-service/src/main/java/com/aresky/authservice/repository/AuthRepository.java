package com.aresky.authservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.authservice.entity.Auth;

public interface AuthRepository extends R2dbcRepository<Auth, Long> {

}
