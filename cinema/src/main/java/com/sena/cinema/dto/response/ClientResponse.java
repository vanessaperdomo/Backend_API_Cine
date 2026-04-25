package com.sena.cinema.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class ClientResponse {

    private UUID clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate registeredAt;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}