package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Period;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsList;
import at.technikum.boutique.hotel.apiservice.openapi.api.RoomsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RoomsController implements RoomsApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseEntity<RoomsList> getAvailableRooms(Period availabilityPeriod, Integer startIndex, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
