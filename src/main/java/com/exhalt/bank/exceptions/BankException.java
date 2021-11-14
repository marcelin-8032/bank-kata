package com.exhalt.bank.exceptions;

import com.exhalt.bank.enums.ResultEnum;

import javax.persistence.criteria.CriteriaBuilder;

public class BankException extends RuntimeException {

    private Integer code;


    public BankException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BankException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
