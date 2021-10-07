package ru.bootcamp.banksystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bootcamp.banksystem.model.Account;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AccountDto {
    private Long id;
    private String number;
    private BigDecimal balance;

    public AccountDto(Account account) {
        this.id= account.getId();
        this.number= account.getNumber();
        this.balance=account.getBalance();
    }
}
