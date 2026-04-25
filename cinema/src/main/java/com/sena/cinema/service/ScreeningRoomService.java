package com.sena.cinema.service;

import com.sena.cinema.dto.request.ScreeningRoomRequest;
import com.sena.cinema.dto.response.ScreeningRoomResponse;

import java.util.List;
import java.util.UUID;

public interface ScreeningRoomService {

    ScreeningRoomResponse create(ScreeningRoomRequest request);

    ScreeningRoomResponse findById(UUID id);

    List<ScreeningRoomResponse> findAll();

    ScreeningRoomResponse update(UUID id, ScreeningRoomRequest request);

    void delete(UUID id);
}