package com.exhalt.bank.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    BALANCE_INSUFFICIENT(1, "Business Error: Account balance is not sufficient."),

    NEGATIVE_AMOUNT(2,"Business Error: Negative amounts are not allowed.");


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
