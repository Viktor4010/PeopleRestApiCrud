package ru.ivanov.PeopleRestApiCrud.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.PeopleRestApiCrud.dto.PersonDTO;
import ru.ivanov.PeopleRestApiCrud.exceptions.PersonNotCreatedException;
import ru.ivanov.PeopleRestApiCrud.models.Person;
import ru.ivanov.PeopleRestApiCrud.services.PeopleServices;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleController {

    private final PeopleServices peopleServices;
    private final ModelMapper mapper;

    @Autowired
    public PeopleController(PeopleServices peopleServices, ModelMapper mapper) {
        this.peopleServices = peopleServices;
        this.mapper = mapper;
    }

    @GetMapping("people")
    public ResponseEntity<List<PersonDTO>> getPeople() {
        List<PersonDTO> response = peopleServices.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("people/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") int id) {
        PersonDTO foundPerson = convertToPersonDTO(peopleServices.findById(id));

        return ResponseEntity.ok(foundPerson);
    }

    @PostMapping("people")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            createErrorMessage(bindingResult);

        peopleServices.create(convertToPerson(personDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("people/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        peopleServices.deleteById(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("people/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable("id") int id, @RequestBody @Valid PersonDTO personDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            createErrorMessage(bindingResult);

        peopleServices.update(convertToPerson(personDTO), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return mapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    private void createErrorMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError error : fieldErrors) {
                errMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new PersonNotCreatedException(errMsg.toString());
        }
    }
}
