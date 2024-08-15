package com.aresky.mailservice.mappers.http;

import com.aresky.mailservice.dto.response.MailBoxDetails;
import com.aresky.mailservice.dto.response.MailBoxResponse;
import com.aresky.mailservice.entity.MailBox;
import com.aresky.mailservice.utils.DateUtils;

public class MailBoxMapper {
    public static MailBoxResponse toMailBoxResponse(MailBox mailBox) {
        return MailBoxResponse.builder()
                .id(mailBox.getId())
                .customer(CustomerMapper.toCustomerResponse(mailBox.getCustomer()))
                .totalMail(mailBox.getTotalMail())
                .unrepliedMailCount(mailBox.getUnrepliedMailCount())
                .createdAt(DateUtils.formatDateTime(mailBox.getCreatedAt()))
                .updatedAt(DateUtils.formatDateTime(mailBox.getUpdatedAt()))
                .build();
    }

    public static MailBoxDetails toMailBoxDetails(MailBox mailBox) {
        return MailBoxDetails.builder()
                .id(mailBox.getId())
                .customer(CustomerMapper.toCustomerResponse(mailBox.getCustomer()))
                .totalMail(mailBox.getTotalMail())
                .unrepliedMailCount(mailBox.getUnrepliedMailCount())
                .mailList(mailBox.getMailList().stream().map(MailMapper::toMailResponse).toList())
                .createdAt(DateUtils.formatDateTime(mailBox.getCreatedAt()))
                .updatedAt(DateUtils.formatDateTime(mailBox.getUpdatedAt()))
                .build();
    }

    public static MailBox toMailBox(Integer customerId, Integer staffId) {
        return MailBox.builder()
                .customerId(customerId)
                .staffId(staffId)
                .totalMail(0)
                .unrepliedMailCount(0)
                .createdAt(DateUtils.now())
                .updatedAt(DateUtils.now())
                .build();
    }
}
