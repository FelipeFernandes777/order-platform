package br.com.order_platform.domain.person.dtos;

import br.com.order_platform.domain.person.enums.Status;
import lombok.NonNull;

import java.time.LocalDate;

public record CreatePersonDTO(
        @NonNull String firstName,
        @NonNull String firsName,
        @NonNull String lastName,
        @NonNull String document,
        @NonNull LocalDate birthDate,
        @NonNull Status status
) {
}
