package ru.bootcamp.banksystem.repository;

import ru.bootcamp.banksystem.model.Account;

import java.math.BigDecimal;

public interface AccountRepository {

    /**
     * Возвращает баланс по id счета
     * @param accountId
     * @return
     */
    BigDecimal getBalance(long accountId);

    /**
     * Получение счета по его id
     * @param accountId
     * @return
     */
    Account getByAccountId(long accountId);


    /**
     * Сохранение нового счета или обновление существующего
     * @param account
     */
    void save(Account account);
}
