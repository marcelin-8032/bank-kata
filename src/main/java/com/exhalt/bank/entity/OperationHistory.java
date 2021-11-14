package com.exhalt.bank.entity;

import com.exhalt.bank.enums.Operations;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class OperationHistory extends Account implements Serializable {

    private Operations operation;

    public OperationHistory(Double balance, LocalDateTime date, Double amountToDepositOrWithdraw, Customer customer, Operations operation) {
       super(balance,date, amountToDepositOrWithdraw,customer);
        this.operation=operation;
    }


}
