package com.exhalt.bank.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;

    private String customerName;
}
