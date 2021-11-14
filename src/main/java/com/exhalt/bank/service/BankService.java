package com.exhalt.bank.service;

import com.exhalt.bank.entity.Account;
import com.exhalt.bank.entity.OperationHistory;
import com.exhalt.bank.entity.Withdrawal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankService {

    ResponseEntity<Account> save(Account amount);

    ResponseEntity<Withdrawal> withdrawalAmount(Long accountId, Withdrawal withdrawal);

    ResponseEntity<List<OperationHistory>> findAllOperationHistory();

}
