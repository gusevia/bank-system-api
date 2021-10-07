package ru.bootcamp.banksystem.repository;

import org.springframework.stereotype.Repository;
import ru.bootcamp.banksystem.model.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class CardRepositoryImpl implements CardRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Card> getAllFromAccount(long accountId) {

        List<Card> resultList = em.createNamedQuery(Card.ALL_FROM_ACCOUNT, Card.class)
                .setParameter("accountId", accountId)
                .getResultList();

        return resultList;
    }

    @Override
    public List<Card> getAllFromUser() {

        List<Card> resultList = em.createNamedQuery(Card.ALL_FROM_USER, Card.class)
                .getResultList();


        return resultList;
    }

    @Override
    public void  save(Card card) {
        if (card.getId() != null) {
            em.merge(card);
        }
        em.persist(card);



    }

}

