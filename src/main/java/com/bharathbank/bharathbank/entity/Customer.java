package com.bharathbank.bharathbank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "custId")
    private int customerId;

    @Column(name = "custName")
    private String customerName;

    @Column(name = "custAge")
    private int customerAge;

    @Column(name = "custCity")
    private String customerCity;

    @Column(name = "custState")
    private String customerState;

    @OneToMany(targetEntity = Account.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "account",referencedColumnName = "custId")
    List<Account> account;
}
