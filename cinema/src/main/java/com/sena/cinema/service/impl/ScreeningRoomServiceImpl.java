package com.sena.cinema.service.impl;

import com.sena.cinema.dto.request.ScreeningRoomRequest;
import com.sena.cinema.dto.response.ScreeningRoomResponse;
import com.sena.cinema.exception.DuplicateResourceException;
import com.sena.cinema.exception.ResourceNotFoundException;
import com.sena.cinema.mapper.ScreeningRoomMapper;
import com.sena.cinema.model.ScreeningRoom;
import com.sena.cinema.repository.ScreeningRoomRepository;
import com.sena.cinema.service.ScreeningRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScreeningRoomServiceImpl implements ScreeningRoomService {

    private final ScreeningRoomRepository screeningRoomRepository;
    private final ScreeningRoomMapper screeningRoomMapper;

    @Override
    @Transactional
    public ScreeningRoomResponse create(ScreeningRoomRequest request) {
        if (screeningRoomRepository.existsByRoomName(request.getRoomName())) {
            throw new DuplicateResourceException(
                "Ya existe una sala con el nombre: " + request.getRoomName()
            );
        }
        ScreeningRoom room = screeningRoomMapper.toEntity(request);
        return screeningRoomMapper.toResponse(screeningRoomRepository.save(room));
    }

    @Override
    @Transactional(readOnly = true)
    public ScreeningRoomResponse findById(UUID id) {
        ScreeningRoom room = screeningRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Sala no encontrada con id: " + id
                ));
        return screeningRoomMapper.toResponse(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScreeningRoomResponse> findAll() {
        return screeningRoomRepository.findAll()
                .stream()
                .map(screeningRoomMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public ScreeningRoomResponse update(UUID id, ScreeningRoomRequest request) {
        ScreeningRoom room = screeningRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Sala no encontrada con id: " + id
                ));

        // Si cambió el nombre, verificar que no lo use otra sala
        if (!room.getRoomName().equalsIgnoreCase(request.getRoomName())
                && screeningRoomRepository.existsByRoomNameAndRoomIdNot(
                        request.getRoomName(), id)) {
            throw new DuplicateResourceException(
                "Ya existe una sala con el nombre: " + request.getRoomName()
            );
        }

        screeningRoomMapper.updateEntity(room, request);
        return screeningRoomMapper.toResponse(screeningRoomRepository.save(room));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!screeningRoomRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Sala no encontrada con id: " + id
            );
        }
        screeningRoomRepository.deleteById(id);
    }
}