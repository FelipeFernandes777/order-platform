package br.com.order_platform.controller;

import br.com.order_platform.domain.person.dtos.CreatePersonDTO;
import br.com.order_platform.domain.person.dtos.GetAllPersonByStatusDTO;
import br.com.order_platform.domain.person.dtos.GetAllPersonsDTO;
import br.com.order_platform.domain.person.dtos.GetPersonByID;
import br.com.order_platform.domain.person.dtos.UpdatePersonDTO;
import br.com.order_platform.domain.person.enums.Status;
import br.com.order_platform.domain.person.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Operation(summary = "Lista todas as pessoas")
    @GetMapping
    public ResponseEntity<List<GetAllPersonsDTO>> getAllPersons(Pageable pageable) {
        return ResponseEntity.ok(this.personService.getAllPersons(pageable));
    }

    @Operation(summary = "Lista pessoas com status ACTIVE")
    @GetMapping("/status/active")
    public ResponseEntity<List<GetAllPersonByStatusDTO>> getAllPersonsWhenStatusIsEqualsActive(Pageable pageable) {
        return ResponseEntity.ok(this.personService.getAllPersonsWhenStatusIsEqualsActive(pageable));
    }

    @Operation(summary = "Lista pessoas com status INACTIVE")
    @GetMapping("/status/inactive")
    public ResponseEntity<List<GetAllPersonByStatusDTO>> getAllPersonsWhenStatusIsEqualsInactive(Pageable pageable) {
        return ResponseEntity.ok(this.personService.getAllPersonsWhenStatusIsEqualsInactive(pageable));
    }

    @Operation(summary = "Lista pessoas com status DISABLE")
    @GetMapping("/status/disabled")
    public ResponseEntity<List<GetAllPersonByStatusDTO>> getAllPersonsWhenStatusIsEqualsDisabled(Pageable pageable) {
        return ResponseEntity.ok(this.personService.getAllPersonsWhenStatusIsEqualsDisabled(pageable));
    }

    @Operation(summary = "Busca uma pessoa pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<GetPersonByID> findPersonById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.personService.findPersonById(id));
    }

    @Operation(summary = "Cria uma pessoa")
    @PostMapping
    public ResponseEntity<GetPersonByID> createPerson(@RequestBody CreatePersonDTO value) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.createPerson(value));
    }

    @Operation(summary = "Atualiza os dados de uma pessoa")
    @PutMapping("/{id}")
    public ResponseEntity<GetPersonByID> updatePerson(@PathVariable Integer id, @RequestBody UpdatePersonDTO value) {
        return ResponseEntity.ok(this.personService.updatePerson(id, value));
    }

    @Operation(summary = "Realiza soft delete de uma pessoa")
    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<Void> safeDeletePersonById(@PathVariable Integer id) {
        this.personService.safeDeletePersonById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Altera o status de uma pessoa")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changePersonStatus(@PathVariable Integer id, @RequestParam Status newStatus) {
        this.personService.changePersonStatus(id, newStatus);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove uma pessoa permanentemente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        this.personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
