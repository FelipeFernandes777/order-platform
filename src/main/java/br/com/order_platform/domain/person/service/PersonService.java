package br.com.order_platform.domain.person.service;

import br.com.order_platform.domain.person.Person;
import br.com.order_platform.domain.person.PersonRepository;
import br.com.order_platform.domain.person.dtos.*;
import br.com.order_platform.domain.person.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public non-sealed class PersonService implements IPersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public List<GetAllPersonsDTO> getAllPersons(Pageable pageable) throws RuntimeException {
        List<Person> person = this.repository.findAll();
        if(person.isEmpty()) {
            return List.of();
        }

        return person.stream()
                .map(GetAllPersonsDTO::new)
                .toList();
    }

    @Override
    public List<GetAllPersonByStatusDTO> getAllPersonsWhenStatusIsEqualsActive(Pageable pageable) throws RuntimeException {
        List<Person> person = this.repository.findAllPersonByStatus(Status.ACTIVE,pageable);

        if(person.isEmpty()) {
            throw new RuntimeException("Person Not Found");
        }

        return person.stream()
                .map(GetAllPersonByStatusDTO::new)
                .toList();
    }

    @Override
    public List<GetAllPersonByStatusDTO> getAllPersonsWhenStatusIsEqualsInactive(Pageable pageable) throws RuntimeException {
        List<Person> person = this.repository.findAllPersonByStatus(Status.INACTIVE,pageable);

        if(person.isEmpty()) {
            throw new RuntimeException("Person Not Found");
        }

        return person.stream()
                .map(GetAllPersonByStatusDTO::new)
                .toList();
    }


    @Override
    public List<GetAllPersonByStatusDTO> getAllPersonsWhenStatusIsEqualsDisabled(Pageable pageable) throws RuntimeException {
        List<Person> person = this.repository.findAllPersonByStatus(Status.DISABLE,pageable);

        if(person.isEmpty()) {
            throw new RuntimeException("Person Not Found");
        }

        return person.stream()
                .map(GetAllPersonByStatusDTO::new)
                .toList();
    }

    @Override
    public GetPersonByID findPersonById(Integer id) throws RuntimeException {
        if(id <= 0) {
            throw new RuntimeException("ID is invalid");
        }
        Optional<Person> person = this.repository.findById(id);
        if(!person.isPresent()) {
            throw new RuntimeException("Person Not Found");
        }else {
         return new GetPersonByID(person.get());
        }
    }

    @Override
    public GetPersonByID createPerson(CreatePersonDTO value) throws RuntimeException {
        Person person = new Person(value);
        this.repository.save(person);
        return new GetPersonByID(person);
    }

    @Override
    public GetPersonByID updatePerson(Integer id, UpdatePersonDTO value) throws RuntimeException {

        if(id <= 0) {
            throw new RuntimeException("ID is invalid");
        }

        Person person = this.repository.findById(id).orElseThrow(RuntimeException::new);
        person.update(value);
        this.repository.save(person);

        return new GetPersonByID(person);
    }

    @Override
    public void safeDeletePersonById(Integer id) throws RuntimeException {
        if(id <= 0) {
            throw new RuntimeException("ID is invalid");
        }
        Person person = this.repository.findById(id).orElseThrow(RuntimeException::new);
        person.safeDelete();
        this.repository.save(person);
    }

    @Override
    public void changePersonStatus(Integer id, Status newStatus) {
        if(id <= 0) {
            throw new RuntimeException("ID is invalid");
        }
        Person person = this.repository.findById(id).orElseThrow(RuntimeException::new);
        person.changeStatus(newStatus);
        this.repository.save(person);
    }

    @Override
    public void deletePerson(Integer id) throws RuntimeException {
        if(id <= 0) {
            throw new RuntimeException("ID is invalid");
        }
        Person person = this.repository.findById(id).orElseThrow(RuntimeException::new);
        this.repository.delete(person);
    }
}
