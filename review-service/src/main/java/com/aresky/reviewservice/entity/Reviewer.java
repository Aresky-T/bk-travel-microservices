package com.aresky.reviewservice.entity;

import io.netty.util.internal.StringUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("reviewer")
public class Reviewer {

    @Id
    private Integer id;

    @Column("full_name")
    private String fullName;

    @Column("avatar_url")
    private String avatarUrl;

    @Column("account_id")
    private Integer accountId;

    public Reviewer(String fullName, String avatarUrl, Integer accountId) {
        this.fullName = !StringUtil.isNullOrEmpty(fullName) ? fullName : "Người dùng ẩn danh";
        this.avatarUrl = avatarUrl;
        this.accountId = accountId;
    }
}
