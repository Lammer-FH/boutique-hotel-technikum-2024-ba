package at.technikum.boutique.hotel.apiservice.extras.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "extra")
public class ExtraEntity {

    @Id
    private Short id;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String description;
}