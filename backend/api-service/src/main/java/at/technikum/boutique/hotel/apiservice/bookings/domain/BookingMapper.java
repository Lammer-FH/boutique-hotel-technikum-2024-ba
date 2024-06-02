package at.technikum.boutique.hotel.apiservice.bookings.domain;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Period;
import lombok.val;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    default List<Period> mapToPeriods(final List<BookingEntity> bookings) {
        return bookings.stream()
                .map(booking -> new Period(convert(booking.getStartDate()), convert(booking.getEndDate())))
                .collect(Collectors.toList());
    }

    private LocalDate convert(Date date) {
        val zoneId = ZoneId.systemDefault();
        val instant = date.toInstant();
        return LocalDate.ofInstant(instant, zoneId);
    }
}