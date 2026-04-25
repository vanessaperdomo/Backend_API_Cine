package com.sena.cinema.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class RentalRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private UUID clientId;

    @NotNull(message = "El ID de la película es obligatorio")
    private UUID movieId;

    @NotNull(message = "El ID de la sala es obligatorio")
    private UUID roomId;

    private LocalDate rentalDate;

    private LocalDate returnDate;

    @Pattern(
        regexp = "active|returned|overdue",
        message = "El estado debe ser: active, returned u overdue"
    )
    private String status;
}