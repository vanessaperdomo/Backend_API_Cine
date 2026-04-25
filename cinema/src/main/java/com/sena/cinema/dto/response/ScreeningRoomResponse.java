package com.sena.cinema.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class ScreeningRoomResponse {

    private UUID roomId;
    private String roomName;
    private Short capacity;
    private String roomType;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}