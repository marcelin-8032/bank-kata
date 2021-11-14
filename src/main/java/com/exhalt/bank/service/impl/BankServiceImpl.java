package com.exhalt.bank.service.impl;

import com.exhalt.bank.entity.Account;
import com.exhalt.bank.entity.OperationHistory;
import com.exhalt.bank.entity.Withdrawal;
import com.exhalt.bank.enums.Operations;
import com.exhalt.bank.enums.ResultEnum;
import com.exhalt.bank.exceptions.BankException;
import com.exhalt.bank.repository.AccountRepository;
import com.exhalt.bank.repository.OperationRepository;
import com.exhalt.bank.service.BankService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    private final OperationRepository operationRepository;


    public BankServiceImpl(AccountRepository bankRepository, OperationRepository operationRepository) {
        this.accountRepository = bankRepository;
        this.operationRepository = operationRepository;
    }


    @Override
    public ResponseEntity<Account> save(Account account) {
        log.info("accountDeposited: " + account.getCustomer());
        accountRepository.save(account);

        List<OperationHistory> histories = new ArrayList<>();
        histories.add(new OperationHistory(account.getBalance(), account.getDate(), account.getActualAmount(), account.getCustomer(), Operations.DEPOSIT));
        operationRepository.saveAll(histories);

        return ResponseEntity.ok(account);

    }

    @Override
    public ResponseEntity<Withdrawal> withdrawalAmount(Long accountId, Withdrawal withdrawal) {

        Account account = accountRepository.findById(accountId).get();
        log.debug("accountToWithdraw. Id: " + account.getId() + "" + account.getCustomer());

        if (withdrawal.getAmountToWithdraw() > 0 && withdrawal.getAmountToWithdraw() <= account.getBalance()) {

            account.setBalance(account.getBalance() - withdrawal.getAmountToWithdraw());
            account.setActualAmount(-withdrawal.getAmountToWithdraw());
            account.setDate(LocalDateTime.now());
            accountRepository.save(account);

            List<OperationHistory> histories = new ArrayList<>();
            histories.add(new OperationHistory(account.getBalance(), account.getDate(), account.getActualAmount(), account.getCustomer(), Operations.WITHDRAWAL));

            operationRepository.saveAll(histories);
        } else {
            log.error("Dear customer: " + account.getCustomer() + " withdrawal amount is more than your account balance. Id: " + accountId);
            throw new BankException(ResultEnum.NEGATIVE_AMOUNT);
        }

        return ResponseEntity.ok(withdrawal);
    }


    @Override
    public ResponseEntity<List<OperationHistory>> findAllOperationHistory() {

        log.debug("get all list of all operations");

        List<OperationHistory> operationHistoryList = new ArrayList<>();
        operationRepository.findAll().iterator().forEachRemaining(operationHistoryList::add);

        return ResponseEntity.ok(operationHistoryList);
    }

}
