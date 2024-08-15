package com.aresky.mailservice.service.mail;

import com.aresky.mailservice.dto.request.MailReplyRequest;
import com.aresky.mailservice.dto.request.MailRequest;
import com.aresky.mailservice.dto.response.CustomerMailDetails;
import com.aresky.mailservice.dto.response.MailResponse;
import com.aresky.mailservice.dto.response.PaginationWrapper;
import com.aresky.mailservice.dto.response.StaffMailDetails;
import com.aresky.mailservice.entity.Mail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMailService {
    Mono<Void> send(MailRequest request);
    Mono<Void> reply(MailReplyRequest request);
    Mono<List<Mail>> getAllMails(Integer boxId, Integer limit, Integer offset);
    Mono<List<MailResponse>> getAllMailResponses(Integer boxId, Integer limit, Integer offset);
    Mono<PaginationWrapper<MailResponse>> getAllMailsForCustomer(String customerEmail, Integer page, Integer size);
    Mono<CustomerMailDetails> getMailDetailsForCustomer(String customerEmail, Integer mailId);
    Mono<StaffMailDetails> getMailDetailsForStaff(String staffEmail, Integer mailId);
    Mono<Void> deleteMail(Integer mailId);
    Mono<MailResponse> markMailAsRead(String staffEmail, Integer mailId);
}
