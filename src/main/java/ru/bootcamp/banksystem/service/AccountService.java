package ru.bootcamp.banksystem.service;

import ru.bootcamp.banksystem.dto.AccountDto;
import ru.bootcamp.banksystem.model.Account;

import java.math.BigDecimal;

public interface AccountService {


    /**
     * Получение баланса счета по его id
     * @param accountId
     * @return AccountDto
     */
    AccountDto getBalance(long accountId);

    /**
     * Получение счета по его id
     * @param accountId
     * @return Account
     */
    Account  getByAccountId(long accountId);

    /**
     * Увеличение баланса на заданную величину
     * @param accountId
     * @param amount
     * @return AccountDto
     */
    AccountDto increaseBalance(Long accountId, BigDecimal amount);

}

