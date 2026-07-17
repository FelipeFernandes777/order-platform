package br.com.order_platform.domain.person;

import br.com.order_platform.domain.person.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllPersonByStatus(Status status, Pageable pageable);
}
