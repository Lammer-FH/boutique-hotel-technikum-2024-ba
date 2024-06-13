package at.technikum.boutique.hotel.apiservice.hotels.domain;

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
@Table(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String address;

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

    @Column(nullable = false)
    private String directions;

    @Column(name = "contact_phone_number", nullable = false, length = 20)
    private String contactPhoneNumber;
}