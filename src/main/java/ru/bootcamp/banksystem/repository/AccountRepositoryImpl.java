package ru.bootcamp.banksystem.repository;

import org.springframework.stereotype.Repository;
import ru.bootcamp.banksystem.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public BigDecimal getBalance(long accountId) {
        return em.createNamedQuery(Account.GET_ACCOUNT, Account.class)
                .setParameter("accountId", accountId).getSingleResult().getBalance();
    }

    @Override
    public Account getByAccountId(long accountId) {

        return em.createNamedQuery(Account.GET_ACCOUNT, Account.class)
                .setParameter("accountId", accountId)
                .getSingleResult();
    }

    @Override
    public void save(Account account) {

        if (account.getId() != null) {
            em.merge(account);
        } else {
            em.persist(account);
        }

    }
}

