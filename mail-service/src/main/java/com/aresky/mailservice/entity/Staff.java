package com.aresky.mailservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("staff")
public class Staff {

    @Id
    private Integer id;

    @Column("full_name")
    private String fullName;

    @Column("email")
    private String email;

    @Column("avatar_url")
    private String avatarUrl;
}
