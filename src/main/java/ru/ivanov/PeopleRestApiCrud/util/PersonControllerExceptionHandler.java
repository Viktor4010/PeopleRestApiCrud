package ru.ivanov.PeopleRestApiCrud.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ivanov.PeopleRestApiCrud.exceptions.PersonNotCreatedException;
import ru.ivanov.PeopleRestApiCrud.exceptions.PersonNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PersonControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handler(PersonNotFoundException e) {
        PersonErrorResponse person_not_found = new PersonErrorResponse("Person Not found", LocalDateTime.now());
        return new ResponseEntity<>(person_not_found, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handler(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
