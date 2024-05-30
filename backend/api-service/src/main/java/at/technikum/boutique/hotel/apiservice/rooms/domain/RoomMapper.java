package at.technikum.boutique.hotel.apiservice.rooms.domain;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomResponse;
import at.technikum.boutique.hotel.apiservice.openapi.DTO.RoomsPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "hotel.latitude", target = "hotel.position.latitude")
    @Mapping(source = "hotel.longitude", target = "hotel.position.longitude")
    RoomResponse mapToDto(RoomEntity entity);

    default RoomsPage mapToRoomsPage(Page<RoomEntity> page) {
        return new RoomsPage()
                .totalElements(BigDecimal.valueOf(page.getTotalElements()))
                .totalPages(page.getTotalPages())
                .pageNumber(page.getNumber() + 1)
                .pageSize(page.getSize())
                .rooms(page.map(this::mapToDto).getContent());
    }
}