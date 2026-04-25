package com.sena.cinema.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClientRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Size(max = 150)
    private String email;

    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String phone;
}