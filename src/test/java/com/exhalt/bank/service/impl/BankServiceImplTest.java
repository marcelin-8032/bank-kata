package com.exhalt.bank.service.impl;

import com.exhalt.bank.entity.Account;
import com.exhalt.bank.entity.Customer;
import com.exhalt.bank.entity.OperationHistory;
import com.exhalt.bank.entity.Withdrawal;
import com.exhalt.bank.exceptions.BankException;
import com.exhalt.bank.repository.AccountRepository;
import com.exhalt.bank.repository.OperationRepository;
import com.exhalt.bank.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BankServiceImplTest {


//    @InjectMocks
    private BankService bankService;

    @Mock
    AccountRepository accountRepository;
    @Mock
    OperationRepository operationRepository;

    private Account account;

    private Customer customer;

    private OperationHistory operationHistory;

    @BeforeEach
    void setUp() {

        account = new Account();
        account.setId(1L);
        account.setBalance(1250.55);
        account.setDate(LocalDateTime.now());
        account.setActualAmount(1000D);

        customer = new Customer();
        customer = new Customer(1L, "David Rogers");
        account.setCustomer(customer);

        MockitoAnnotations.openMocks(this);
        bankService = new BankServiceImpl(accountRepository, operationRepository);
    }


    @Test
    public void shouldSaveADeposit() {

        when(accountRepository.save(account)).thenReturn(account);

        ResponseEntity<Account> accountResponseEntity = bankService.save(account);

        //when
        assertEquals(HttpStatus.OK, accountResponseEntity.getStatusCode());
        verify(accountRepository, times(1)).save(account);

    }


    @Test
    public void shouldWithDrawMoney() {

        //given

        Optional<Account> accountOptional = Optional.of(account);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAmountToWithdraw(500D);

        when(accountRepository.findById(anyLong())).thenReturn(accountOptional);
        ResponseEntity<Withdrawal> withdrawalResponseEntity = bankService.withdrawalAmount(1L, withdrawal);

        //when
        assertEquals(HttpStatus.OK, withdrawalResponseEntity.getStatusCode());
        verify(accountRepository, times(1)).save(account);

    }

    @Test
    public void shouldThrowExceptionWhenWithDrawIsMoreThanBalance() {

        Optional<Account> accountOptional = Optional.of(account);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAmountToWithdraw(2000D);

        when(accountRepository.findById(anyLong())).thenReturn(accountOptional);

        //when
        assertThrows(BankException.class, () ->
        {
            bankService.withdrawalAmount(1L, withdrawal);
        });


        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void shouldGetAllOperationHistories() {

        OperationHistory operationDeposit = new OperationHistory();
        OperationHistory operationWithDraw1 = new OperationHistory();
        OperationHistory operationWithDraw2 = new OperationHistory();

        List<OperationHistory> operationHistoryList = new ArrayList<>();
        operationHistoryList.add(operationDeposit);
        operationHistoryList.add(operationWithDraw1);
        operationHistoryList.add(operationWithDraw2);
    }

}