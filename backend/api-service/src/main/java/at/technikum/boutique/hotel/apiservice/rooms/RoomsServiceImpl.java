package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsPage;
import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomEntity;
import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository repository;
    private final RoomMapper mapper;

    @Override
    public ResponseEntity<RoomsPage> getRooms(Integer startIndex, Integer limit) {
        Page<RoomEntity> entityPage = repository.findAll(getPageable(startIndex, limit));
        var roomsPage = mapper.mapToRoomsPage(entityPage);
        return ResponseEntity.ok(roomsPage);
    }

    private Pageable getPageable(Integer startIndex, Integer limit) {
        return PageRequest.of(startIndex - 1, limit);
    }
}