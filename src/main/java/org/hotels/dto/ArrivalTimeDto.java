package org.hotels.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalTimeDto {
    @Schema(description = "Время заезда", example = "14:00")
    @Pattern(regexp = "^[0-2]\\d:[0-5]\\d$", message = "Некорректный формат времени (HH:mm)")
    private String checkIn;

    @Schema(description = "Время выезда", example = "12:00")
    @Pattern(regexp = "^[0-2]\\d:[0-5]\\d$", message = "Некорректный формат времени (HH:mm)")
    private String checkOut;
}
