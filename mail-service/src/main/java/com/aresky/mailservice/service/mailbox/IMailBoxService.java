package com.aresky.mailservice.service.mailbox;

import com.aresky.mailservice.dto.response.MailBoxDetails;
import com.aresky.mailservice.dto.response.MailBoxResponse;
import com.aresky.mailservice.entity.MailBox;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMailBoxService {
    Mono<List<MailBoxResponse>> getAllMailBoxResponsesForStaff(String staffEmail);
    Mono<MailBoxDetails> getMailBoxDetails(Integer id);
    Mono<MailBox> getMailBoxBy(Integer id);
    Mono<MailBox> getMailBoxBy(Integer customerId, Integer staffId);
    Mono<Void> deleteMailBox(Integer id);
    Mono<MailBox> save(MailBox mailBox);
}
