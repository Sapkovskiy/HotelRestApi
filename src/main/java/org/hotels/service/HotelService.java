package org.hotels.service;

import org.hotels.dto.HotelBriefDto;
import org.hotels.exception.HotelNotFoundException;
import org.hotels.model.Hotel;
import org.hotels.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<HotelBriefDto> getAllHotelsBrief() {
        List<Hotel> hotels = hotelRepository.findAll();

        return hotels.stream()
                .map(hotel -> new HotelBriefDto(
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getDescription(),
                        hotel.getAddress().getHouseNumber() + " " + hotel.getAddress().getStreet() + ", " +
                        hotel.getAddress().getCity() + ", " +
                        hotel.getAddress().getPostCode() + ", " +
                        hotel.getAddress().getCounty(),
                        hotel.getContacts().getPhone()
                ))
                .collect(Collectors.toList());
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Отель с ID " + id + " не найден"));
    }

    public HotelBriefDto saveHotel(Hotel hotel) {
        Hotel save = hotelRepository.save(hotel);
        HotelBriefDto dto = new HotelBriefDto();
        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setDescription(save.getDescription());
        dto.setAddress(save.getAddress().getHouseNumber() + " " + save.getAddress().getStreet() +
                       ", " + save.getAddress().getCity() + ", " + save.getAddress().getPostCode() + ", " + save.getAddress().getCounty());
        dto.setPhone(save.getContacts().getPhone());
        return dto;
    }

    @Transactional
    public Hotel addAmenities(Long id, Set<String> amenities) {
        Hotel hotel = getHotelById(id);
        hotel.getAmenities().addAll(amenities);
        return hotelRepository.save(hotel);
    }

    public Map<String, Long> getHistogram(String param) {
        List<Hotel> hotels = getAllHotels();
        return switch (param.toLowerCase()) {
            case "brand" -> hotels.stream().collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));
            case "city" -> hotels.stream().collect(Collectors.groupingBy(h -> h.getAddress().getCity(), Collectors.counting()));
            case "county" -> hotels.stream().collect(Collectors.groupingBy(h -> h.getAddress().getCounty(), Collectors.counting()));
            case "amenities" -> hotels.stream()
                    .flatMap(h -> h.getAmenities().stream())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            default -> throw new IllegalArgumentException("Неверный параметр: " + param);
        };
    }
}

