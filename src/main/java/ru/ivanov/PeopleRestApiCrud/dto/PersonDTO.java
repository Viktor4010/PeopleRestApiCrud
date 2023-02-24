package ru.ivanov.PeopleRestApiCrud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDTO {

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 20, message = "name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "age should be greater then 0")
    private int age;

}
