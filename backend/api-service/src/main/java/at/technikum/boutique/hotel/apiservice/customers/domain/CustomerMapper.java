package at.technikum.boutique.hotel.apiservice.customers.domain;

import at.technikum.boutique.hotel.apiservice.openapi.DTO.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer mapToDto(CustomerEntity entity);

    @Mapping(source = "customerId", target = "id")
    CustomerEntity mapToEntity(Integer customerId, Customer dto);

    @Mapping(target = "id", ignore = true)
    CustomerEntity mapToEntity(Customer dto);
}