package ru.ivanov.PeopleRestApiCrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.PeopleRestApiCrud.exceptions.PersonNotFoundException;
import ru.ivanov.PeopleRestApiCrud.models.Person;
import ru.ivanov.PeopleRestApiCrud.repositories.PeopleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleServices {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleServices(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        System.out.println("try to find all people and return it");
        return peopleRepository.findAll();
    }

    public Person findById(int id) {

        Optional<Person> foundPerson = peopleRepository.findById(id);

        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void create(Person person) {
        System.out.println("save person to database");
        enrichPerson(person);
        peopleRepository.save(person);
    }

    @Transactional
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(Person updatedPerson, int id) {
        updatedPerson.setId(id);
        enrichPerson(updatedPerson);
        peopleRepository.save(updatedPerson);
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setCreatedWho("admin");
    }


}
