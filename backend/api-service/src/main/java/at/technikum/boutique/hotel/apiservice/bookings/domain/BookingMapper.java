package at.technikum.boutique.hotel.apiservice.bookings.domain;

import at.technikum.boutique.hotel.apiservice.customers.domain.CustomerEntity;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.Booking;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.BookingResponse;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.Period;
import at.technikum.boutique.hotel.apiservice.rooms.domain.RoomEntity;
import lombok.val;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "customerEntity", target = "customer")
    @Mapping(source = "roomEntity", target = "room")
    @Mapping(source = "booking.reservationPeriod.start", target = "startDate", qualifiedByName = "LocalDateToDate")
    @Mapping(source = "booking.reservationPeriod.end", target = "endDate", qualifiedByName = "LocalDateToDate")
    BookingEntity mapToEntity(Booking booking, CustomerEntity customerEntity, RoomEntity roomEntity);

    @Mapping(source = "entity.startDate", target = "reservationPeriod.start", qualifiedByName = "DateToLocalDate")
    @Mapping(source = "entity.endDate", target = "reservationPeriod.end", qualifiedByName = "DateToLocalDate")
    @Mapping(source = "entity.room.hotel.latitude", target = "room.hotel.position.latitude")
    @Mapping(source = "entity.room.hotel.longitude", target = "room.hotel.position.longitude")
    BookingResponse mapToResponseDTO(BookingEntity entity);

    default List<Period> mapToPeriods(final List<BookingEntity> bookings) {
        return bookings.stream()
                .map(booking -> new Period(convert(booking.getStartDate()), convert(booking.getEndDate())))
                .collect(Collectors.toList());
    }

    @Named("DateToLocalDate")
    default LocalDate convert(Date date) {
        val zoneId = ZoneId.systemDefault();
        val instant = date.toInstant();
        return LocalDate.ofInstant(instant, zoneId);
    }

    @Named("LocalDateToDate")
    default Date convert(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}