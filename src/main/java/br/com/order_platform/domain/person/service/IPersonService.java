package br.com.order_platform.domain.person.service;

import br.com.order_platform.domain.person.Person;
import br.com.order_platform.domain.person.dtos.*;

import br.com.order_platform.domain.person.enums.Status;
import org.springframework.data.domain.Pageable;
import java.util.List;

public sealed interface IPersonService permits PersonService {
    /**
     * Está função pega todas as entidades da tabela de <b>persons</b>
     * @param pageable
     * @see {@link GetAllPersonsDTO}
     * @throws RuntimeException
     * @return {@code  List<GetAllPersonsDTO>}
     * **/
    List<GetAllPersonsDTO> getAllPersons(Pageable pageable);
    /**
     * Está função pega todas as entidades da tabela de <b>persons</b> que estão com o status {@code STATUS.ACTIVE}
     * @param pageable Pageable
     * @see {@link GetAllPersonByStatusDTO}
     * @throws RuntimeException
     * @return {@code  List<GetAllPersonByStatusDTO>}
     * **/
    List<GetAllPersonByStatusDTO> getAllPersonsWhenStatusIsEqualsActive(Pageable pageable);
    /**
     * Está função pega todas as entidades da tabela de <b>persons</b> que estão com o status {@code STATUS.INACTIVE}
     * @param pageable Pageable
     * @see {@link GetAllPersonByStatusDTO}
     * @throws RuntimeException
     * @return {@code  List<GetAllPersonByStatusDTO>}
     * **/
    List<GetAllPersonByStatusDTO> getAllPersonsWhenStatusIsEqualsInactive(Pageable pageable);
    /**
     * Está função pega todas as entidades da tabela de <b>persons</b> que estão com o status {@code STATUS.DISABLE}
     * @param pageable Pageable
     * @see {@link GetAllPersonByStatusDTO}
     * @throws RuntimeException
     * @return {@code  List<GetAllPersonByStatusDTO>}
     * **/
    List<GetAllPersonByStatusDTO> getAllPersonsWhenStatusIsEqualsDisabled(Pageable pageable);
    /**
     * Está função busca o usuário por ID
     * @param id Integer
     * @see {@link GetPersonByID}
     * @throws RuntimeException
     * @return {@code  GetPersonByID}
     * **/
    GetPersonByID findPersonById(Integer id);
    /**
     * Está função cria um usuário
     * @param value CreatePersonDTO
     * @see {@link CreatePersonDTO}
     * @throws RuntimeException
     * @return {@code  GetPersonByID}
     * **/
    GetPersonByID createPerson(CreatePersonDTO value);
    /**
     *
     * Está função atualiza os campos do usuário
     * @param value Person
     * @throws RuntimeException
     * @see {@link Person}
     * @return {@code Person}
     * */
    GetPersonByID updatePerson(Integer id, UpdatePersonDTO value);
    /**
     * Está função faz um soft delete do usuário
     * @param id Integer
     * @throws RuntimeException
     * */
    void safeDeletePersonById(Integer id);
    /**
     * Está função ativa um usuário
     * @param id Integer
     * @param newStatus Status
     * @see {@link Status}
     * @throws RuntimeException
     * */
    void changePersonStatus(Integer id, Status newStatus);
    /**
     * Está função apaga o usuário do banco de dados
     * @param id Integer
     * @throws RuntimeException
     * */
    void deletePerson(Integer id);
}
