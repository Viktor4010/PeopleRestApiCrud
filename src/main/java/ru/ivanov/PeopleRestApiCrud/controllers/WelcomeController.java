package ru.ivanov.PeopleRestApiCrud.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "welcome", produces = MediaType.APPLICATION_JSON_VALUE)
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Hello Viktor! You are the best!!!!";
    }

}
