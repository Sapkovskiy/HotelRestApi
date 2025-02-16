package org.hotels.dto;

import lombok.Builder;
import org.hotels.model.Address;
import org.hotels.model.ArrivalTime;
import org.hotels.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDto {

    @Schema(description = "Уникальный идентификатор отеля", example = "1")
    private Long id;

    @Schema(description = "Название отеля", example = "DoubleTree by Hilton Minsk")
    @NotBlank(message = "Название отеля не должно быть пустым")
    private String name;

    @Schema(description = "Описание отеля", example = "A luxury hotel in Minsk")
    private String description;

    @Schema(description = "Бренд отеля", example = "Hilton")
    @NotBlank(message = "Бренд не должен быть пустым")
    private String brand;

    @Schema(description = "Адрес отеля")
    @NotNull(message = "Адрес обязателен")
    private Address addressDto;

    @Schema(description = "Контакты отеля")
    @NotNull(message = "Контактные данные обязательны")
    private Contact contactsDto;

    @Schema(description = "Время заселения и выселения")
    private ArrivalTime arrivalTimeDto;

    @Schema(description = "Список удобств", example = "[\"WiFi\", \"Parking\"]")
    private Set<String> amenities;
}