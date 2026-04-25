package com.sena.cinema.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class RentalResponse {

    private UUID rentalId;

    // Datos del cliente (no el objeto completo, solo lo necesario)
    private UUID clientId;
    private String clientFullName;

    // Datos de la película
    private UUID movieId;
    private String movieTitle;

    // Datos de la sala
    private UUID roomId;
    private String roomName;

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}