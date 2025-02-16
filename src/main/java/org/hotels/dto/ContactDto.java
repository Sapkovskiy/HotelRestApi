package org.hotels.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class ContactDto {
    @Schema(description = "Номер телефона", example = "+375 17 309-80-00")
    @NotBlank(message = "Телефон обязателен")
    private String phone;

    @Schema(description = "Email отеля", example = "doubletreeminsk.info@hilton.com")
    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    private String email;
}
