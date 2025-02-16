package org.hotels.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Contact {
    @Schema(description = "Номер телефона", example = "+375 17 309-80-00")
    @NotBlank(message = "Телефон обязателен")
    private String phone;

    @Schema(description = "Email отеля", example = "doubletreeminsk.info@hilton.com")
    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    private String email;
}
