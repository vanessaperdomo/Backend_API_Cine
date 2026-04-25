package com.sena.cinema.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MovieRequest {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 100)
    private String genre;

    @NotBlank(message = "El director es obligatorio")
    @Size(max = 150)
    private String director;

    @NotNull(message = "El año de estreno es obligatorio")
    @Min(value = 1888, message = "El año mínimo es 1888")
    private Short releaseYear;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser mayor a 0")
    private Short durationMin;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Short stock;
}