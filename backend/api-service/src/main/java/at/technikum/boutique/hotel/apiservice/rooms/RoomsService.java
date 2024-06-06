package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsPage;
import org.springframework.http.ResponseEntity;

public interface RoomsService {
    ResponseEntity<RoomsPage> getRooms(Integer startIndex, Integer limit);
}