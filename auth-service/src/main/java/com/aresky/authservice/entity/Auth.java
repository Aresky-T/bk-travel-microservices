package com.aresky.authservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "auth")
public class Auth {
    @Id
    @Column("id")
    private Integer id;

    @Column("account_id")
    private Integer accountId;

    @Column("access_token")
    private String accessToken;

    public Auth(Integer accountId, String accessToken) {
        this.accountId = accountId;
        this.accessToken = accessToken;
    }
}
