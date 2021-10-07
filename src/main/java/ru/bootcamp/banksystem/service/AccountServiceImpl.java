package ru.bootcamp.banksystem.service;

import org.springframework.stereotype.Service;
import ru.bootcamp.banksystem.dto.AccountDto;
import ru.bootcamp.banksystem.exception.AccountIdException;
import ru.bootcamp.banksystem.model.Account;
import ru.bootcamp.banksystem.repository.AccountRepository;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto getBalance(long accountId) {

        Account account = getByAccountId(accountId);

        return new AccountDto(account);

    }

    public Account getByAccountId(long accountId) {

        Account account;
        try {
            account = accountRepository.getByAccountId(accountId);
        } catch (RuntimeException e) {
            throw new AccountIdException("Несуществующий id счета : " + accountId);
        }
        return account;
    }


    @Override
    public AccountDto increaseBalance(Long accountId, BigDecimal amount) {

        Account account = getByAccountId(accountId);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        return new AccountDto(account);
    }
}
