package at.technikum.boutique.hotel.apiservice.customers.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    private Integer id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TINYTEXT")
    private String firstName;

    @Column(name = "second_name", nullable = false, columnDefinition = "TINYTEXT")
    private String secondName;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TINYTEXT")
    private String email;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;
}