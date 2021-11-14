package com.exhalt.bank.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    private static Account SINGLE_INSTANCE = null;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double balance;

    @CreatedDate
    private LocalDateTime date;

    private Double actualAmount;

    @OneToOne
    private Customer customer;

    public Account(Double balance, LocalDateTime date, Double actualAmount, Customer customer) {
        this.balance = balance;
        this.date = date;
        this.actualAmount = actualAmount;
        this.customer = customer;
    }

    public Account(Customer customer, Double balance) {
        this.balance = balance;
        this.customer = customer;
    }

}
