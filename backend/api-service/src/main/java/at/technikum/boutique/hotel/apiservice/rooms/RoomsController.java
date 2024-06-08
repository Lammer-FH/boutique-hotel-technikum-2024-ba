package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Period;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsPage;
import at.technikum.boutique.hotel.apiservice.openapi.api.RoomsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomsController implements RoomsApi {

    private final RoomsService service;

    @Override
    public ResponseEntity<RoomsPage> getRooms(Integer startIndex, Integer limit) {
        return service.getRooms(startIndex, limit);
    }

    @Override
    public ResponseEntity<RoomsPage> getAvailableRooms(Period availabilityPeriod, Integer startIndex, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}