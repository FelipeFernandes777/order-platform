package br.com.order_platform.domain.person;

import br.com.order_platform.domain.person.dtos.CreatePersonDTO;
import br.com.order_platform.domain.person.dtos.UpdatePersonDTO;
import br.com.order_platform.domain.person.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "person")
@Entity(name = "person")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Getter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "\"firstName\"",length = 20, columnDefinition = "VARCHAR(20)")
    private String firstName;

    @Column(name = "\"lastName\"",length = 100, columnDefinition = "VARCHAR(100)")
    private String lastName;

    @Column(unique = true, nullable = false, length = 15, columnDefinition = "VARCHAR(15)")
    private String document;

    @Column(name = "\"birthDate\"", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "status", columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Person(CreatePersonDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.birthDate = data.birthDate();
        this.status = data.status();
    }

    public void safeDelete() {
        this.status = Status.DISABLE;
    }

    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

    public Person update(UpdatePersonDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();

        return this;
    }
}
