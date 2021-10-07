package ru.bootcamp.banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bootcamp.banksystem.dto.CardDto;
import ru.bootcamp.banksystem.model.Card;
import ru.bootcamp.banksystem.service.CardService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = CardController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    public final static String REST_URL = "/rest/v1";

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/accounts/{accountId}/cards")
    public ResponseEntity<List<CardDto>> getAllByAccount(@PathVariable long accountId) {

        log.info("account id ={} ", accountId);
        if (accountId == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CardDto> cardsDtoList = cardService.getAllFromAccount(accountId);

        return new ResponseEntity<>(cardsDtoList, HttpStatus.OK);
    }

    @GetMapping("/cards")
    public List<CardDto> getAllByClient() {

        return cardService.getAllFromUser();

    }

    @PostMapping("/accounts/{accountId}/cards")
    public ResponseEntity<CardDto> create(@PathVariable long accountId) {


        Card createdCard = cardService.create(accountId);
        log.info("add new card for accountId: {}", accountId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/v1/cards/{id}")
                .buildAndExpand(createdCard.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new CardDto(createdCard));

    }

}
