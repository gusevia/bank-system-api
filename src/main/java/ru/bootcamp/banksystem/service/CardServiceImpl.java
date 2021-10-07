package ru.bootcamp.banksystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bootcamp.banksystem.dto.CardDto;
import ru.bootcamp.banksystem.exception.AccountIdException;
import ru.bootcamp.banksystem.exception.NoCardException;
import ru.bootcamp.banksystem.model.Account;
import ru.bootcamp.banksystem.model.Card;
import ru.bootcamp.banksystem.repository.AccountRepository;
import ru.bootcamp.banksystem.repository.CardRepository;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {


    private CardRepository cardRepository;
    private AccountRepository accountRepository;


    public CardServiceImpl(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    @Override

    public List<CardDto> getAllFromAccount(long accountId) {
        Account account = getByAccountId(accountId);
        List<Card> cards = cardRepository.getAllFromAccount(accountId);
        if (cards.isEmpty()) {
            throw new NoCardException("У пользователя нет карт");
        }
        return CardDto.createDto(cards);
    }

    @Override
    public List<CardDto> getAllFromUser() {

        List<Card> cards = cardRepository.getAllFromUser();
        if (cards.isEmpty()) {
            throw new NoCardException("У пользователя нет карт");
        }
        return CardDto.createDto(cardRepository.getAllFromUser());

    }

    @Transactional
    @Override
    public Card create(long accountId) {

        Account account = null;
        Card card = null;

        try {
            account = accountRepository.getByAccountId(accountId);
            card = new Card(generateNumber(account), account);
            cardRepository.save(card);

        } catch (Exception ex) {
            throw new AccountIdException("Несуществующий счет");
        }
        return card;
    }

    private String generateNumber(Account account) {
        StringBuilder st = new StringBuilder("2222");
        for (int i = 0; i < 3; i++) {
            st.append((int) (Math.random() * (9999 - 1000) + 1000));
        }

        return st.toString();
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


}
