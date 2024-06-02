package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Period;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomBookedPeriods;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsPage;
import org.springframework.http.ResponseEntity;

public interface RoomsService {
    ResponseEntity<RoomsPage> getRooms(Integer startIndex, Integer limit);
    ResponseEntity<RoomsPage> getAvailableRooms(Period availabilityPeriod, Integer startIndex, Integer limit);
    ResponseEntity<RoomBookedPeriods> getRoomBookedPeriods(Integer roomId, Period period);
}