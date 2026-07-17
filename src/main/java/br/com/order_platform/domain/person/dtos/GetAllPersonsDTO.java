package br.com.order_platform.domain.person.dtos;

import br.com.order_platform.domain.person.Person;
import br.com.order_platform.domain.person.enums.Status;

public record GetAllPersonsDTO(
        String firstName,
        String lastName,
        String document,
        Status status
) {
    public GetAllPersonsDTO(Person person){
        this(person.getFirstName(), person.getLastName(), person.getDocument(), person.getStatus());
    }
}
