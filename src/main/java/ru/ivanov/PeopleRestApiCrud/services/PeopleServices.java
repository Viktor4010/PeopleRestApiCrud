package ru.ivanov.PeopleRestApiCrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.PeopleRestApiCrud.models.Person;
import ru.ivanov.PeopleRestApiCrud.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
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

    public Optional<Person> findById(int id) {
      return peopleRepository.findById(id);
    }

    @Transactional
    public void create(Person person) {
        System.out.println("save person to database");
        peopleRepository.save(person);

    }
}
