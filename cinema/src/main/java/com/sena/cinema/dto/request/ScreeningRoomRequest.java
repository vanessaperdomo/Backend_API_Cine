package com.sena.cinema.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ScreeningRoomRequest {

    @NotBlank(message = "El nombre de la sala es obligatorio")
    @Size(max = 100)
    private String roomName;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private Short capacity;

    @NotBlank(message = "El tipo de sala es obligatorio")
    @Pattern(
        regexp = "standard|3D|IMAX|VIP",
        message = "El tipo debe ser: standard, 3D, IMAX o VIP"
    )
    private String roomType;
}