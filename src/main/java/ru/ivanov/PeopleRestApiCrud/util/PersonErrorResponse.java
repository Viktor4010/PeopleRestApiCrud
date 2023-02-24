package ru.ivanov.PeopleRestApiCrud.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PersonErrorResponse {
    private String msg;
    private LocalDateTime timestamp;
}
