package com.exhalt.bank.controller;


import com.exhalt.bank.entity.Account;
import com.exhalt.bank.entity.OperationHistory;
import com.exhalt.bank.entity.Withdrawal;
import com.exhalt.bank.enums.ResultEnum;
import com.exhalt.bank.exceptions.BankException;
import com.exhalt.bank.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveAmount(@RequestBody Account account) {
        if(account.getBalance()==0){
            throw new BankException(ResultEnum.BALANCE_INSUFFICIENT);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bankService.save(account));
    }

    @PutMapping("/withdrawal/{accountId}")
    public ResponseEntity<Object> withdrawalAmount(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.withdrawalAmount(accountId, withdrawal));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OperationHistory>> getAllOperationHistory() {
        return this.bankService.findAllOperationHistory();
    }
}