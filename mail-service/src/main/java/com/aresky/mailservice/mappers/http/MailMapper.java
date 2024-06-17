package com.aresky.mailservice.mappers.http;

import com.aresky.mailservice.dto.request.MailRequest;
import com.aresky.mailservice.dto.response.CustomerMailDetails;
import com.aresky.mailservice.dto.response.MailResponse;
import com.aresky.mailservice.dto.response.StaffMailDetails;
import com.aresky.mailservice.dto.response.StaffMailResponse;
import com.aresky.mailservice.entity.Customer;
import com.aresky.mailservice.entity.EMailStatus;
import com.aresky.mailservice.entity.Mail;
import com.aresky.mailservice.entity.Staff;
import com.aresky.mailservice.utils.DateUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MailMapper {
    public static Mail toMail(MailRequest request){
        return Mail.builder()
                .subject(request.getSubject())
                .body(request.getBody())
                .status(EMailStatus.NEW)
                .sentAt(ZonedDateTime.now(ZoneId.systemDefault()))
                .isReplied(Boolean.FALSE)
                .build();
    }

    public static MailResponse toMailResponse(Mail mail){
        return MailResponse.builder()
                .id(mail.getId())
                .subject(mail.getSubject())
                .body(mail.getBody())
                .sentAt(DateUtils.formatDateTime(mail.getSentAt()))
                .status(mail.getStatus().name())
                .isReplied(mail.getStatus().equals(EMailStatus.REPLIED))
                .build();
    }

    public static CustomerMailDetails toCustomerMailDetails(Mail mail){
        return CustomerMailDetails.builder()
                .id(mail.getId())
                .subject(mail.getSubject())
                .body(mail.getBody())
                .sentAt(DateUtils.formatDateTime(mail.getSentAt()))
                .reply(MailReplyMapper.toMailReplyResponse(mail.getReply()))
                .build();
    }

    public static CustomerMailDetails toCustomerMailDetails(Mail mail, Staff staff){
        return CustomerMailDetails.builder()
                .id(mail.getId())
                .subject(mail.getSubject())
                .body(mail.getBody())
                .sentAt(DateUtils.formatDateTime(mail.getSentAt()))
                .staff(StaffMapper.toStaffResponse(staff))
                .reply(MailReplyMapper.toMailReplyResponse(mail.getReply()))
                .build();
    }

    public static StaffMailResponse toStaffMailResponse(Mail mail){
        return StaffMailResponse.builder()
                .id(mail.getId())
                .subject(mail.getSubject())
                .body(mail.getBody())
                .sentAt(DateUtils.formatDateTime(mail.getSentAt()))
                .isReplied(mail.getIsReplied())
                .build();
    }

    public static StaffMailDetails toStaffMailDetails(Mail mail){
        return StaffMailDetails.builder()
                .id(mail.getId())
                .boxId(mail.getMailBoxId())
                .subject(mail.getSubject())
                .body(mail.getBody())
                .sentAt(DateUtils.formatDateTime(mail.getSentAt()))
                .status(mail.getStatus().name())
                .reply(MailReplyMapper.toMailReplyResponse(mail.getReply()))
                .build();
    }

    public static StaffMailDetails toStaffMailDetails(Mail mail, Customer customer){
        return StaffMailDetails.builder()
                .id(mail.getId())
                .boxId(mail.getMailBoxId())
                .subject(mail.getSubject())
                .body(mail.getBody())
                .sentAt(DateUtils.formatDateTime(mail.getSentAt()))
                .status(mail.getStatus().name())
                .customer(CustomerMapper.toCustomerResponse(customer))
                .reply(MailReplyMapper.toMailReplyResponse(mail.getReply()))
                .build();
    }
}
