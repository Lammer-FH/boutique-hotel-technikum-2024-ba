package at.technikum.boutique.hotel.apiservice.rooms.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class RoomExtraEntity {

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    @Entity
    @Table(name = "room_extra")
    @IdClass(RoomExtraId.class)
    public class RoomExtra {

        @Id
        @Column(name = "room_id")
        private Integer roomId;

        @Id
        @Column(name = "extra_id")
        private Short extraId;
    }
}