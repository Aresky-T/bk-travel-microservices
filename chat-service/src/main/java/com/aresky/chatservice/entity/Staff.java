package com.aresky.chatservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("staff")
public class Staff {
    @Id
    @Column("id")
    private Integer id;

    @Column("email")
    private String email;

    @Column("full_name")
    private String fullName;

    @Column("avatar_url")
    private String avatarUrl;

    @Column("status")
    private EActivationStatus status;
}
