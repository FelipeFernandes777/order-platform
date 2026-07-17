package br.com.order_platform.domain.person.dtos;

import br.com.order_platform.domain.person.Person;

public record GetAllPersonByStatusDTO(
        String firstName,
        String lastName,
        String document
) {
    public GetAllPersonByStatusDTO(Person person){
        this(person.getFirstName(), person.getLastName(), person.getDocument());
    }
}
