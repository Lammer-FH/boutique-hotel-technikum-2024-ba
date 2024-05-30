package at.technikum.boutique.hotel.apiservice.hotels.domain;

import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "hotel")
public class HotelEntity {

    @Id
    private Integer id;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String address;

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

    @Lob
    @Column(nullable = false)
    private String directions;

    @Column(name = "contact_phone_number", nullable = false, length = 20)
    private String contactPhoneNumber;

    @OneToMany(mappedBy = "hotel")
    private Set<RoomEntity> rooms;
}