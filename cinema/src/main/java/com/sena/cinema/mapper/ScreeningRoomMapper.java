package com.sena.cinema.mapper;

import com.sena.cinema.dto.request.ScreeningRoomRequest;
import com.sena.cinema.dto.response.ScreeningRoomResponse;
import com.sena.cinema.model.ScreeningRoom;
import org.springframework.stereotype.Component;

@Component
public class ScreeningRoomMapper {

    public ScreeningRoom toEntity(ScreeningRoomRequest request) {
        return ScreeningRoom.builder()
                .roomName(request.getRoomName())
                .capacity(request.getCapacity())
                .roomType(request.getRoomType())
                .build();
    }

    public ScreeningRoomResponse toResponse(ScreeningRoom room) {
        return ScreeningRoomResponse.builder()
                .roomId(room.getRoomId())
                .roomName(room.getRoomName())
                .capacity(room.getCapacity())
                .roomType(room.getRoomType())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }

    public void updateEntity(ScreeningRoom room, ScreeningRoomRequest request) {
        room.setRoomName(request.getRoomName());
        room.setCapacity(request.getCapacity());
        room.setRoomType(request.getRoomType());
    }
}