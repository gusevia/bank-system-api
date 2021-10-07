package ru.bootcamp.banksystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bootcamp.banksystem.model.Card;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class CardDto {

    private long id;
    private String number;

    public CardDto(Card card) {
        this.id = card.getId();
        this.number = card.getNumber();
    }

 public static List<CardDto>  createDto(List<Card> card){
        return card.stream().map(CardDto::new).collect(Collectors.toList());
  }
}
