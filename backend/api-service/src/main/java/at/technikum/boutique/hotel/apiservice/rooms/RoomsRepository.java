package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<RoomEntity, Integer>, JpaSpecificationExecutor<RoomEntity> {
}