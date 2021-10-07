package ru.bootcamp.banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.banksystem.dto.AccountDto;
import ru.bootcamp.banksystem.service.AccountService;
import ru.bootcamp.banksystem.util.Operation;


@RestController
@RequestMapping(value = AccountController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public final static String REST_URL = "rest/v1/accounts";

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/{accountId}/balance")
    public ResponseEntity<AccountDto> getBalance(@PathVariable Long accountId)  {
        log.info("account id: {}", accountId);

        if (accountId==0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccountDto accountDto = accountService.getBalance(accountId);

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDto> changeBalance(@RequestBody Operation amount, @PathVariable Long accountId) {
        log.info("Amount for add: {} ", amount.getAmount());

        if (accountId==0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AccountDto accountDto = accountService.increaseBalance(accountId, amount.getAmount());

        return new ResponseEntity<>(accountDto, HttpStatus.OK);

    }

}
