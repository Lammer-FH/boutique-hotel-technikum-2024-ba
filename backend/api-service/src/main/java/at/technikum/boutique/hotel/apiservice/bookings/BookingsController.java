package at.technikum.boutique.hotel.apiservice.bookings;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Booking;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.BookingResponse;
import at.technikum.boutique.hotel.apiservice.openapi.api.BookingsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingsController implements BookingsApi {

    private final BookingsService service;

    @Override
    public ResponseEntity<BookingResponse> createBooking(Booking booking) {
        return service.createBooking(booking);
    }
}
