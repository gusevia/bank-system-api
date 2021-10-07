package ru.bootcamp.banksystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountIdException extends RuntimeException{

    public AccountIdException(String message) {
        super(message);
    }
}
