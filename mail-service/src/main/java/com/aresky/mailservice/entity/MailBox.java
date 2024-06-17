package com.aresky.mailservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mail_box")
public class MailBox {

    @Id
    private Integer id;

    @Column("customer_id")
    private Integer customerId;

    @Column("staff_id")
    private Integer staffId;

    @Column("total_mail")
    private Integer totalMail;

    @Column("unreplied_mail_count")
    private Integer unrepliedMailCount;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Transient
    private Staff staff;

    @Transient
    private Customer customer;

    @Transient
    private List<Mail> mailList = new ArrayList<>();
}
