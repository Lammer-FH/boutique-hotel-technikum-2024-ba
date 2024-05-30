package at.technikum.boutique.hotel.apiservice.rooms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RoomExtraId implements Serializable {
    private Integer roomId;
    private Short extraId;
}