package ru.bootcamp.banksystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Account.GET_ACCOUNT, query = "select ac from Account ac where ac.id=:accountId")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    public static final String GET_ACCOUNT = "Get Account by accountId";
    public static final String GET_BALANCE = "Get balance from account";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 20,max = 20)
    private String number;
    private BigDecimal balance;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Card> cards;
    private Long userId;


}
