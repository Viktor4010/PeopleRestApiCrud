package ru.ivanov.PeopleRestApiCrud.exceptions;

public class PersonNotCreatedException extends RuntimeException {
    public PersonNotCreatedException(String s) {
        super(s);
    }
}
