package org.hotels.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AddressDto {
    @Schema(description = "Номер дома", example = "9")
    @NotNull(message = "Номер дома обязателен")
    private Integer houseNumber;

    @Schema(description = "Улица", example = "Pobediteley Avenue")
    @NotBlank(message = "Улица обязательна")
    private String street;

    @Schema(description = "Город", example = "Minsk")
    @NotBlank(message = "Город обязателен")
    private String city;

    @Schema(description = "Страна", example = "Belarus")
    @NotBlank(message = "Страна обязательна")
    private String county;

    @Schema(description = "Почтовый индекс", example = "220004")
    @NotBlank(message = "Почтовый индекс обязателен")
    private String postCode;
}
