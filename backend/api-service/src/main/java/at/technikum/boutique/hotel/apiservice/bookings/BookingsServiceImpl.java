package at.technikum.boutique.hotel.apiservice.bookings;

import at.technikum.boutique.hotel.apiservice.bookings.domain.BookingMapper;
import at.technikum.boutique.hotel.apiservice.customers.CustomerRepository;
import at.technikum.boutique.hotel.apiservice.customers.domain.CustomerEntity;
import at.technikum.boutique.hotel.apiservice.customers.domain.CustomerMapper;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.Booking;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.BookingResponse;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.Customer;
import at.technikum.boutique.hotel.apiservice.rooms.RoomsRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository repository;
    private final CustomerRepository customerRepository;
    private final RoomsRepository roomsRepository;

    private final CustomerMapper customerMapper;
    private final BookingMapper bookingMapper;


    @Override
    public ResponseEntity<BookingResponse> createBooking(Booking booking) {
        val period = booking.getReservationPeriod();

        // Check if start is before end
        if (period.getStart().isAfter(period.getEnd())) {
            return ResponseEntity.badRequest().body(null);
        }

        // Check if room is not reserved
        val potentialBookings = repository.getBookingsForRoomWithinPeriod(
                period.getStart(),
                period.getEnd(),
                booking.getRoomId());

        if (!potentialBookings.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        val customerEntity = CreateOrUpdateCustomer(booking.getCustomer());

        val roomOpt = roomsRepository.findById(booking.getRoomId());
        if (roomOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        val bookingEntity = bookingMapper.mapToEntity(booking, customerEntity, roomOpt.get());
        val bookingResult = repository.save(bookingEntity);
        val bookingResponse = bookingMapper.mapToResponseDTO(bookingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }

    private CustomerEntity CreateOrUpdateCustomer(Customer customer) {
        CustomerEntity customerEntity = null;
        // Check if user exist
        val customerOpt = customerRepository.findByEmail(customer.getEmail());
        if (customerOpt.isPresent()) {
            val existingCustomer = customerOpt.get();
            customerEntity = customerMapper.mapToEntity(existingCustomer.getId(), customer);
            // Update customer if anything changed
            if (existingCustomer != customerEntity) {
                customerEntity = customerRepository.save(customerEntity);
            }
        }
        return customerEntity;
    }
}
