package com.exhalt.bank.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;

    private String customerName;
}
