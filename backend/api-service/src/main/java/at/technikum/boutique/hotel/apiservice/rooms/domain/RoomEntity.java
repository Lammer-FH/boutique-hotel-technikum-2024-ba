package at.technikum.boutique.hotel.apiservice.rooms.domain;

import at.technikum.boutique.hotel.apiservice.extras.domain.ExtraEntity;
import at.technikum.boutique.hotel.apiservice.hotels.domain.HotelEntity;
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
@Table(name = "room")
public class RoomEntity {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "bed_amount", nullable = false)
    private Byte bedAmount;

    @Column(nullable = false)
    private Integer price;

    @ManyToMany
    @JoinTable(
            name = "room_extra",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private Set<ExtraEntity> extras;
}