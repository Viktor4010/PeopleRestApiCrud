package ru.ivanov.PeopleRestApiCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.PeopleRestApiCrud.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>  {
}
