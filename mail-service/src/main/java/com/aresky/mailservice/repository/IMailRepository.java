package com.aresky.mailservice.repository;

import com.aresky.mailservice.entity.Mail;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IMailRepository extends R2dbcRepository<Mail, Integer> {
}
