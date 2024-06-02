package at.technikum.boutique.hotel.apiservice.rooms;

import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RoomsRepository extends JpaRepository<RoomEntity, Integer>, JpaSpecificationExecutor<RoomEntity> {

    @Query(value = "SELECT r FROM RoomEntity r LEFT OUTER JOIN  BookingEntity b ON r.id = b.room.id " +
            "AND b.endDate >= :#{#start} " +
            "AND b.startDate <= :#{#end} " +
            "WHERE b.room IS NULL"
    )
    Page<RoomEntity> findAllAvailableRooms(@Param("start") LocalDate availabilityPeriodStart, @Param("end") LocalDate availabilityPeriodEnd, Pageable pageable);
}