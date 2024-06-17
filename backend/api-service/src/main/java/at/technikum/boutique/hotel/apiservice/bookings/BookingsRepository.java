package at.technikum.boutique.hotel.apiservice.bookings;

import at.technikum.boutique.hotel.apiservice.bookings.domain.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<BookingEntity, Integer>, JpaSpecificationExecutor<BookingEntity> {

    @Query(value = "SELECT b FROM BookingEntity b" +
            "    WHERE b.room.id = :#{#roomId} " +
            "        AND b.endDate > :#{#start} " +
            "        AND b.startDate < :#{#end} ")
    List<BookingEntity> getBookingsForRoomWithinPeriod(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("roomId") Integer roomId);


}