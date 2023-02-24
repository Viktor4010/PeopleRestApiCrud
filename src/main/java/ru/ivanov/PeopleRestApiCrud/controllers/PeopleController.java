package ru.ivanov.PeopleRestApiCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.PeopleRestApiCrud.models.Person;
import ru.ivanov.PeopleRestApiCrud.services.PeopleServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleController {

    private final PeopleServices peopleServices;

    @Autowired
    public PeopleController(PeopleServices peopleServices) {
        this.peopleServices = peopleServices;
    }

    @GetMapping("people")
    public ResponseEntity<List<Person>> getPeople() {
        List<Person> response = peopleServices.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("people/{id}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable("id") int id) {

        Optional<Person> foundPerson = peopleServices.findById(id);

        return ResponseEntity.ok(foundPerson);
    }

    @PostMapping("people")
    public ResponseEntity<HttpStatus> create(@RequestBody Person person) {

        peopleServices.create(person);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
