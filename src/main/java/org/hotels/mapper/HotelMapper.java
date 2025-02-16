package org.hotels.mapper;

import org.hotels.dto.HotelBriefDto;
import org.hotels.dto.HotelDto;
import org.hotels.model.Address;
import org.hotels.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "address", target = "addressDto")
    @Mapping(source = "contacts", target = "contactsDto")
    @Mapping(source = "arrivalTime", target = "arrivalTimeDto")
    HotelDto toDto(Hotel hotel);

    @Mappings({
            @Mapping(target = "id", source = "hotel.id"),
            @Mapping(target = "name", source = "hotel.name"),
            @Mapping(target = "description", source = "hotel.description"),
            @Mapping(target = "address", source = "hotel.address", qualifiedByName = "formatAddress"),
            @Mapping(target = "phone", source = "hotel.contacts.phone")
    })
    HotelBriefDto mapHotelToBriefDto(Hotel hotel);

    @Named("formatAddress")
    default String formatAddress(Address address) {
        return address.getHouseNumber() + " " + address.getStreet() + ", " +
               address.getCity() + ", " +
               address.getPostCode() + ", " +
               address.getCounty();
    }
}



