package com.aresky.mailservice.service.mailbox;

import com.aresky.mailservice.dto.response.MailBoxDetails;
import com.aresky.mailservice.dto.response.MailBoxResponse;
import com.aresky.mailservice.entity.Customer;
import com.aresky.mailservice.entity.Mail;
import com.aresky.mailservice.entity.MailBox;
import com.aresky.mailservice.exception.ExceptionMessage;
import com.aresky.mailservice.exception.MailException;
import com.aresky.mailservice.mappers.http.MailBoxMapper;
import com.aresky.mailservice.repository.IMailBoxRepository;
import com.aresky.mailservice.service.customer.ICustomerService;
import com.aresky.mailservice.service.mail.IMailService;
import com.aresky.mailservice.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MailBoxServiceImp implements IMailBoxService {
    @Autowired
    private IMailBoxRepository mailBoxRepository;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private ICustomerService customerService;

    private IMailService mailService;

    @Autowired
    public void setMailService(@Lazy IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public Mono<List<MailBoxResponse>> getAllMailBoxResponsesForStaff(String staffEmail) {
        return staffService.getStaffByEmail(staffEmail)
                .flatMapMany(staff -> mailBoxRepository.findByStaffId(staff.getId()))
                .flatMap(mailBox -> customerService.getById(mailBox.getCustomerId())
                        .map(customer -> {
                            mailBox.setCustomer(customer);
                            return MailBoxMapper.toMailBoxResponse(mailBox);
                        }))
                .collectList();
    }

    @Override
    public Mono<MailBoxDetails> getMailBoxDetails(Integer id) {
        return mailBoxRepository.findById(id)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_MAILBOX_ID)))
                .flatMap(mailBox -> mailService.getAllMails(id, 10, 0)
                        .zipWith(customerService.getById(mailBox.getCustomerId()))
                        .map(tuple -> {
                            List<Mail> mailList = tuple.getT1();
                            Customer customer = tuple.getT2();

                            mailBox.setCustomer(customer);
                            mailBox.setMailList(mailList);
                            return MailBoxMapper.toMailBoxDetails(mailBox);
                        }));
    }

    @Override
    public Mono<MailBox> getMailBoxBy(Integer id) {
        return mailBoxRepository.findById(id);
    }

    @Override
    public Mono<MailBox> getMailBoxBy(Integer customerId, Integer staffId) {
        return mailBoxRepository.existsByCustomerIdAndStaffId(customerId, staffId)
                .flatMap(exists -> {
                    if (exists) {
                        return mailBoxRepository.findByCustomerIdAndStaffId(customerId, staffId);
                    } else {
                        return mailBoxRepository
                                .save(MailBoxMapper.toMailBox(customerId, staffId))
                                .then(getMailBoxBy(customerId, staffId));
                    }
                });
    }

    @Override
    public Mono<Void> deleteMailBox(Integer id) {
        return mailBoxRepository.existsById(id).flatMap(exists -> {
            if(exists){
                return mailBoxRepository.deleteById(id).then();
            }

            return Mono.error(new MailException(ExceptionMessage.INVALID_MAILBOX_ID));
        });
    }

    @Transactional
    @Override
    public Mono<MailBox> save(MailBox mailBox) {
        return mailBoxRepository.save(mailBox);
    }
}
