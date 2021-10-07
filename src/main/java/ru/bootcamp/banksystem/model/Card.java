package ru.bootcamp.banksystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Card.ALL_FROM_USER, query = "SELECT c FROM Card c"),
        @NamedQuery(name = Card.ALL_FROM_ACCOUNT, query = "SELECT c FROM Card c  WHERE c.account.id=:accountId")
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    public static final String ALL_FROM_USER = "Card.getAllFromUser";
    public static final String ALL_FROM_ACCOUNT = "Card.getAllFromAccount";

    public Card(String number, Account account) {
        this.number = number;
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 16,max = 16)
    private String number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", account=" + account +
                '}';
    }
}
