package com.bharathbank.bharathbank.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "custId")
    private int customerId;
    @Column(name = "acctNo")
    private int accountNo;
    @Column(name = "acctType")
    private String accountType;
    @Column(name = "acctbal")
    private int accountBal;

}
