package ru.ivanov.PeopleRestApiCrud.exceptions;

import java.time.LocalDateTime;

public class PersonNotFoundException extends RuntimeException {
    private String msg;

    // todo
    // add timestamp to mistake
    public PersonNotFoundException(String msg) {
        super(msg);
    }
}
