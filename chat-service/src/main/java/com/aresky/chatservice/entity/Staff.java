package com.aresky.chatservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("staff")
public class Staff {
    @Id
    @Column("id")
    private Integer id;

    @Column("account_id")
    private Integer accountId;

    @Column("status")
    private EActivationStatus status;

    public Staff(Integer staffId, Integer accountId){
        this.id = staffId;
        this.accountId = accountId;
    }
}
