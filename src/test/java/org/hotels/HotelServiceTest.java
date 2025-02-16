package org.hotels;

import org.hotels.dto.HotelBriefDto;
import org.hotels.exception.HotelNotFoundException;
import org.hotels.model.Address;
import org.hotels.model.Contact;
import org.hotels.model.Hotel;
import org.hotels.repository.HotelRepository;
import org.hotels.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hilton");
        hotel.setDescription("Luxury hotel");
        hotel.setBrand("Hilton");
        hotel.setAddress(new Address(9, "Pobediteley Avenue", "Minsk", "Belarus", "220004"));
        hotel.setContacts(new Contact("+375 17 309-80-00", "info@hilton.com"));
        hotel.setAmenities(Set.of("WiFi", "Parking"));
    }

    @Test
    void getAllHotels_ShouldReturnList() {
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        List<Hotel> hotels = hotelService.getAllHotels();
        assertEquals(1, hotels.size());
        assertEquals("Hilton", hotels.get(0).getName());
    }

    @Test
    void getHotelById_WhenHotelExists_ShouldReturnHotel() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        Hotel foundHotel = hotelService.getHotelById(1L);
        assertNotNull(foundHotel);
        assertEquals(1L, foundHotel.getId());
    }

    @Test
    void getHotelById_WhenHotelNotFound_ShouldThrowException() {
        when(hotelRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(HotelNotFoundException.class, () -> hotelService.getHotelById(99L));
    }

    @Test
    void saveHotel_ShouldReturnHotelBriefDto() {
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        HotelBriefDto savedDto = hotelService.saveHotel(hotel);
        assertNotNull(savedDto);
        assertEquals("Hilton", savedDto.getName());
    }

    @Test
    void getHistogram_ShouldReturnHistogram() {
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        Map<String, Long> histogram = hotelService.getHistogram("brand");
        assertEquals(1, histogram.get("Hilton"));
    }

    @Test
    void getHistogram_WhenInvalidParam_ShouldThrowException() {
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        assertThrows(IllegalArgumentException.class, () -> hotelService.getHistogram("invalid"));
    }
}
