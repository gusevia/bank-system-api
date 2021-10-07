package ru.bootcamp.banksystem.repository;


import ru.bootcamp.banksystem.model.Card;

import java.util.List;


public interface CardRepository  {

    /**
     * Получение списка карт по счету
     * @param bankAccountId
     * @return List<Card>
     */

    List<Card> getAllFromAccount(long bankAccountId);

    /**
     * Получение списка всех карт пользователя

     */
    List<Card> getAllFromUser();

    /**
     * Выпуск новой карты по счету
     * @param card
     */
    void save(Card card);
}
