package com.aresky.chatservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("conversation")
public class Conversation {
    @Id
    @Column("id")
    private Integer id;

    @Column("customer_id")
    private Integer customerId;

    @Column("staff_id")
    private Integer staffId;

    @Column("latest_customer_msg_id")
    private Integer latestCustomerMessageId;

    @Column("latest_staff_msg_id")
    private Integer latestStaffMessageId;

    @Column("new_customer_msg_count")
    private Integer newCustomerMessageCount;

    @Column("new_staff_msg_count")
    private Integer newStaffMessageCount;

    @Column("created_at")
    private ZonedDateTime createdAt;
}
