package com.aresky.mailservice.mappers.http;

import com.aresky.mailservice.dto.request.MailReplyRequest;
import com.aresky.mailservice.dto.response.MailReplyResponse;
import com.aresky.mailservice.entity.MailReply;
import com.aresky.mailservice.utils.DateUtils;

public class MailReplyMapper {
    public static MailReply toMailReply(MailReplyRequest request) {
        return MailReply.builder()
                .subject(request.getSubject())
                .body(request.getBody())
                .mailId(request.getMailId())
                .repliedAt(DateUtils.now())
                .build();
    }

    public static MailReplyResponse toMailReplyResponse(MailReply reply){
        if(reply == null) return null;

        return MailReplyResponse.builder()
                .id(reply.getId())
                .mailId(reply.getMailId())
                .subject(reply.getSubject())
                .body(reply.getBody())
                .repliedAt(DateUtils.formatDateTime(reply.getRepliedAt()))
                .build();
    }
}
