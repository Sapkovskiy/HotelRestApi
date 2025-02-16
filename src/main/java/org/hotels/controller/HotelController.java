package org.hotels.controller;

import lombok.RequiredArgsConstructor;
import org.hotels.dto.HotelBriefDto;
import org.hotels.dto.HotelDto;
import org.hotels.mapper.HotelMapper;
import org.hotels.model.Hotel;
import org.hotels.service.HotelService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/property-view/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    public ResponseEntity<List<HotelBriefDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotelsBrief());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(hotelMapper.toDto(hotelService.getHotelById(id)));
    }

    @PostMapping
    public ResponseEntity<HotelBriefDto> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.saveHotel(hotel));
    }

    @PostMapping("/{id}/amenities")
    public ResponseEntity<HotelDto> addAmenities(
            @PathVariable @Min(1) Long id,
            @RequestBody @NotEmpty(message = "Список удобств не должен быть пустым") Set<String> amenities) {
        return ResponseEntity.ok(hotelMapper.toDto(hotelService.addAmenities(id, amenities)));
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHistogram(param));
    }
}
