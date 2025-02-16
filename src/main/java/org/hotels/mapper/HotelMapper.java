package org.hotels.mapper;

import org.hotels.dto.HotelDto;
import org.hotels.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "address", target = "address")
    @Mapping(source = "contacts", target = "contacts")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    HotelDto toDto(Hotel hotel);
}



