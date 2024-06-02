package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.bookings.BookingsRepository;
import at.technikum.boutique.hotel.apiservice.bookings.domain.BookingEntity;
import at.technikum.boutique.hotel.apiservice.bookings.domain.BookingMapper;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.Period;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomBookedPeriods;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsPage;
import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomEntity;
import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository repository;
    private final BookingsRepository bookingsRepository;
    private final RoomMapper mapper;
    private final BookingMapper bookingMapper;

    @Override
    public ResponseEntity<RoomsPage> getRooms(Integer startIndex, Integer limit) {
        Page<RoomEntity> entityPage = repository.findAll(getPageable(startIndex, limit));
        var roomsPage = mapper.mapToRoomsPage(entityPage);
        return ResponseEntity.ok(roomsPage);
    }

    @Override
    public ResponseEntity<RoomsPage> getAvailableRooms(Period availabilityPeriod, Integer startIndex, Integer limit) {
        Page<RoomEntity> entityPage = repository.findAllAvailableRooms(
                availabilityPeriod.getStart(),
                availabilityPeriod.getEnd(),
                getPageable(startIndex, limit));
        var roomsPage = mapper.mapToRoomsPage(entityPage);
        return ResponseEntity.ok(roomsPage);
    }

    @Override
    public ResponseEntity<RoomBookedPeriods> getRoomBookedPeriods(Integer roomId, Period period) {
        List<BookingEntity> bookingEntities = bookingsRepository.getBookingsForRoomWithinPeriod(
                period.getStart(),
                period.getEnd(),
                roomId);

        val periods = bookingMapper.mapToPeriods(bookingEntities);

        RoomBookedPeriods response = new RoomBookedPeriods()
                .roomId(roomId)
                .requestedPeriod(period);
        response.setBooked(periods);

        return ResponseEntity.ok(response);
    }

    private Pageable getPageable(Integer startIndex, Integer limit) {
        return PageRequest.of(startIndex - 1, limit);
    }
}