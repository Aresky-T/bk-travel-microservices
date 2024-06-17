package com.aresky.mailservice.repository;

import com.aresky.mailservice.entity.MailReply;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface IMailReplyRepository extends R2dbcRepository<MailReply, Integer> {
    Mono<MailReply> findByMailId(Integer mailId);
}
