package at.technikum.boutique.hotel.apiservice.bookings;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Booking;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.BookingResponse;
import org.springframework.http.ResponseEntity;

public interface BookingsService {
    ResponseEntity<BookingResponse> createBooking(Booking booking);
}