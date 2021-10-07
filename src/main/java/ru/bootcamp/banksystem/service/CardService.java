package ru.bootcamp.banksystem.service;

import ru.bootcamp.banksystem.dto.CardDto;
import ru.bootcamp.banksystem.model.Card;

import java.util.List;

public interface CardService {
    /**
     * получаем карты Dto по номеру счета
     * @param accountId
     * @return List<CardDto>
     */
    List<CardDto> getAllFromAccount(long accountId);

    /**
     * Получаем карты Dto пользователя по всем счетам
     * @return List<CardDto>
     */
    List<CardDto> getAllFromUser();

    /**
     * Создаем карту по номеру счета
     * @param accountId
     * @return Card
     */
    Card create(long accountId);
}
