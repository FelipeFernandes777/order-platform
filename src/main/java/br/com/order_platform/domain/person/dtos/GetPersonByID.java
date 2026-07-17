package br.com.order_platform.domain.person.dtos;

import br.com.order_platform.domain.person.Person;
import br.com.order_platform.domain.person.enums.Status;

import java.time.LocalDate;

public record GetPersonByID(
        String firstName,
        String lastName,
        String document,
        LocalDate birthDate,
        Status status
) {
    public GetPersonByID(Person person) {
        this(person.getFirstName(), person.getLastName(), person.getDocument(), person.getBirthDate(), person.getStatus());
    }
}
