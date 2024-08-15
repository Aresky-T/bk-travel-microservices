package com.aresky.mailservice.service.mail;

import com.aresky.mailservice.dto.request.MailReplyRequest;
import com.aresky.mailservice.dto.request.MailRequest;
import com.aresky.mailservice.dto.response.CustomerMailDetails;
import com.aresky.mailservice.dto.response.MailResponse;
import com.aresky.mailservice.dto.response.PaginationWrapper;
import com.aresky.mailservice.dto.response.StaffMailDetails;
import com.aresky.mailservice.entity.*;
import com.aresky.mailservice.exception.ExceptionMessage;
import com.aresky.mailservice.exception.MailException;
import com.aresky.mailservice.mappers.http.MailMapper;
import com.aresky.mailservice.mappers.http.MailReplyMapper;
import com.aresky.mailservice.repository.IMailReplyRepository;
import com.aresky.mailservice.repository.IMailRepository;
import com.aresky.mailservice.service.customer.ICustomerService;
import com.aresky.mailservice.service.mailbox.IMailBoxService;
import com.aresky.mailservice.service.staff.IStaffService;
import com.aresky.mailservice.service.thymeleaf.IThymeleafService;
import com.aresky.mailservice.utils.DateUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImp implements IMailService {

    @Autowired
    private IMailRepository mailRepository;

    @Autowired
    private IMailReplyRepository mailReplyRepository;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private IThymeleafService thymeleafService;

    @Autowired
    private JavaMailSender javaMailSender;

    private IMailBoxService mailBoxService;

    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    public void setMailBoxService(@Lazy IMailBoxService mailBoxService) {
        this.mailBoxService = mailBoxService;
    }

    @Override
    public Mono<Void> send(MailRequest request) {
        Mono<Customer> customerMono = customerService.register(request.getEmail(), request.getFullName());
        Mono<Staff> staffMono = staffService.getRandomStaff();

        return Mono.zip(customerMono, staffMono)
                .switchIfEmpty(Mono.error(new MailException("Sent mail failed!")))
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Staff staff = tuple.getT2();
                    return mailBoxService.getMailBoxBy(customer.getId(), staff.getId());
                })
                .flatMap(mailBox -> {
                    Mail mail = MailMapper.toMail(request);
                    mail.setMailBoxId(mailBox.getId());
                    return mailRepository.save(mail)
                            .flatMap(newMail -> {
                                // update total_mail field
                                mailBox.setTotalMail(mailBox.getTotalMail() + 1);

                                // update unreplied_mail_count field
                                mailBox.setUnrepliedMailCount(mailBox.getUnrepliedMailCount() + 1);

                                // update updated_at field
                                ZonedDateTime newMailSentAt = newMail.getSentAt();
                                mailBox.setUpdatedAt(newMailSentAt != null ? newMailSentAt : DateUtils.now());
                                return  mailBoxService.save(mailBox).then();
                            });
                })
                .onErrorResume(err -> Mono.error(new MailException(err.getMessage())));
    }

    @Override
    public Mono<Void> reply(MailReplyRequest request) {
        MailReply reply = MailReplyMapper.toMailReply(request);
        Integer mailId = request.getMailId();
        return getMail(mailId)
                .flatMap(mail -> {
                    if(mail.getStatus().equals(EMailStatus.REPLIED)){
                        throw new MailException("This mail has been replied!");
                    }

                    return mailReplyRepository.save(reply)
                           .flatMap(newReply -> {
                                mail.setStatus(EMailStatus.REPLIED);
                                return mailRepository.save(mail);
                            });
                })
                .flatMap(mail -> mailBoxService.getMailBoxBy(mail.getMailBoxId()))
                .flatMap(mailBox -> {
                    mailBox.setUnrepliedMailCount(mailBox.getUnrepliedMailCount() - 1);
                    return mailBoxService.save(mailBox);
                })
                .flatMap(mailBox -> customerService.getById(mailBox.getCustomerId()))
                .flatMap(customer -> {
                    String recipient = customer.getEmail();
                    String subject = request.getSubject();
                    String body = request.getBody();

                    Map<String, Object> variables = new HashMap<>();
                    Document doc = Jsoup.parse(body);
                    variables.put("mailContent", doc.html());

                    String content = thymeleafService.createContent("mail-sender-template", variables);
                    return sendMailWithHtmlTemplate(recipient, subject, content);
                })
                .onErrorResume(err -> Mono.error(new MailException(err.getMessage())));
    }

    @Override
    public Mono<List<Mail>> getAllMails(Integer boxId, Integer limit, Integer offset) {
        String query = "SELECT * FROM mail\n" +
                "WHERE mail_box_id = :boxId\n" +
                "ORDER BY sent_at DESC\n" +
                "LIMIT :limit\n" +
                "OFFSET :offset;";

        return databaseClient.sql(query)
                .bind("boxId", boxId)
                .bind("limit", limit)
                .bind("offset", offset)
                .fetch().all()
                .map(row -> Mail.builder()
                        .id((Integer) row.get("id"))
                        .subject((String) row.get("subject"))
                        .body((String) row.get("body"))
                        .sentAt((ZonedDateTime) row.get("sent_at"))
                        .status(EMailStatus.valueOf((String) row.get("status")))
                        .mailBoxId(boxId)
                        .build())
                .collectList();
    }

    @Override
    public Mono<List<MailResponse>> getAllMailResponses(Integer boxId, Integer limit, Integer offset) {
        return this.getAllMails(boxId, limit, offset)
                .flatMapMany(Flux::fromIterable)
                .map(MailMapper::toMailResponse)
                .collectList();
    }

    @Override
    public Mono<PaginationWrapper<MailResponse>> getAllMailsForCustomer(String customerEmail, Integer page, Integer size) {
        return customerService.getByEmail(customerEmail)
                .flatMap(customer -> {
                    String query1 = "SELECT M.id, M.subject, M.body, M.sent_at, M.status, B.id as box_id, B.customer_id, B.staff_id\n" +
                            "FROM mail M\n" +
                            "INNER JOIN mail_box B\n" +
                            "ON M.mail_box_id = B.id\n" +
                            "WHERE B.customer_id = :customerId\n" +
                            "ORDER BY M.sent_at DESC\n" +
                            "LIMIT :limit\n" +
                            "OFFSET :offset;";

                    String query2 = "SELECT COUNT(*) AS total_elements\n" +
                            "FROM mail M\n" +
                            "INNER JOIN mail_box B\n" +
                            "ON M.mail_box_id = B.id\n" +
                            "WHERE B.customer_id = :customerId\n" +
                            "GROUP BY B.customer_id;";

                    Mono<List<MailResponse>> firstQueryMono = databaseClient.sql(query1)
                            .bind("customerId", customer.getId())
                            .bind("limit", size)
                            .bind("offset", page * size)
                            .fetch()
                            .all()
                            .map(row -> Mail.builder()
                                    .id((Integer) row.get("id"))
                                    .subject((String) row.get("subject"))
                                    .body((String) row.get("body"))
                                    .sentAt((ZonedDateTime) row.get("sent_at"))
                                    .status(EMailStatus.valueOf((String) row.get("status")))
                                    .mailBoxId((Integer) row.get("box_id"))
                                    .build())
                            .map(MailMapper::toMailResponse)
                            .collectList();

                    Mono<Long> secondQueryMono = databaseClient.sql(query2)
                            .bind("customerId", customer.getId())
                            .fetch()
                            .one()
                            .map(row -> (Long) row.get("total_elements"));

                    return Mono.zip(firstQueryMono, secondQueryMono);
                })
                .onErrorMap(err -> new MailException(err.getMessage()))
                .map(result -> new PaginationWrapper<MailResponse>(result.getT1(), result.getT2(), page, size));
    }

    @Override
    public Mono<CustomerMailDetails> getMailDetailsForCustomer(String customerEmail, Integer mailId) {
        Mono<Customer> customerMono = customerService.getByEmail(customerEmail);
        Mono<Mail> mailMono = getMail(mailId);

        return Mono.zip(customerMono, mailMono)
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Mail mail = tuple.getT2();

                    return mailBoxService.getMailBoxBy(mail.getMailBoxId())
                            .flatMap(mailBox -> {
                                if (!Objects.equals(mailBox.getCustomerId(), customer.getId())){
                                    throw new MailException(ExceptionMessage.ACCESS_DENIED_MESSAGE);
                                }

                                return Mono.just(mailBox);
                            })
                            .flatMap(mailBox -> staffService.getStaffById(mailBox.getStaffId()))
                            .flatMap(staff -> {
                                if (!mail.getStatus().equals(EMailStatus.REPLIED)){
                                    mail.setReply(null);
                                    return Mono.just(MailMapper.toCustomerMailDetails(mail, staff));
                                }


                                return mailReplyRepository.findByMailId(mailId)
                                        .map(reply -> {
                                            mail.setReply(reply);
                                            return MailMapper.toCustomerMailDetails(mail, staff);
                                        });
                            });
                });
    }

    @Override
    public Mono<StaffMailDetails> getMailDetailsForStaff(String staffEmail, Integer mailId) {
        Mono<Staff> staffMono = staffService.getStaffByEmail(staffEmail);
        Mono<Mail> mailMono = getMail(mailId);
        return Mono.zip(staffMono, mailMono)
                .flatMap(tuple -> {
                    Staff staff = tuple.getT1();
                    Mail mail = tuple.getT2();

                    return mailBoxService.getMailBoxBy(mail.getMailBoxId())
                            .flatMap(mailBox -> {
                                if (!Objects.equals(mailBox.getStaffId(), staff.getId())){
                                    throw new MailException(ExceptionMessage.ACCESS_DENIED_MESSAGE);
                                }

                                mailBox.setStaff(staff);
                                return customerService.getById(mailBox.getCustomerId())
                                        .map(customer -> {
                                            mailBox.setCustomer(customer);
                                            return mailBox;
                                        });
                            })
                            .flatMap(mailBox -> {
                                Customer customer = mailBox.getCustomer();

                                if (!mail.getStatus().equals(EMailStatus.REPLIED)){
                                    mail.setReply(null);
                                    return Mono.just(MailMapper.toStaffMailDetails(mail, customer));
                                }

                                return mailReplyRepository.findByMailId(mailId)
                                        .map(reply -> {
                                            mail.setReply(reply);
                                            return MailMapper.toStaffMailDetails(mail, customer);
                                        });
                            });
                });
    }

    @Override
    public Mono<Void> deleteMail(Integer mailId) {
        return mailRepository.existsById(mailId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_MAIL_ID)))
                .flatMap(exists -> mailRepository.deleteById(mailId));
    }

    @Override
    public Mono<MailResponse> markMailAsRead(String staffEmail, Integer mailId) {
        return staffService.getStaffByEmail(staffEmail)
                .zipWith(this.getMail(mailId))
                .flatMap(tuple -> {
                    Staff staff = tuple.getT1();
                    Mail mail = tuple.getT2();

                    return  mailBoxService.getMailBoxBy(mail.getMailBoxId())
                            .flatMap(mailBox -> {
                                if (!Objects.equals(mailBox.getStaffId(), staff.getId())){
                                    return Mono.error(new MailException(ExceptionMessage.ACCESS_DENIED_MESSAGE));
                                }

                                EMailStatus mailStatus = mail.getStatus();

                                if(mailStatus.equals(EMailStatus.NEW)){
                                    mail.setStatus(EMailStatus.READ);
                                    return mailRepository.save(mail);
                                }

                                return Mono.just(mail);
                            })
                            .map(MailMapper::toMailResponse);
                });
    }

    private Mono<Mail> getMail(Integer mailId) {
        return mailRepository.findById(mailId)
               .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_MAIL_ID)));
    }

    private Mono<Void> sendMailWithHtmlTemplate(String recipient, String subject, String body){
        return Mono.fromRunnable(() -> {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
                helper.setTo(recipient);
                helper.setFrom(email, "Bk Travel");
                helper.setSubject(subject);
                helper.setText(body, true);
                javaMailSender.send(message);
            } catch (MessagingException | UnsupportedEncodingException ex) {
                ex.printStackTrace(System.err);
            }
        });
    }
}
