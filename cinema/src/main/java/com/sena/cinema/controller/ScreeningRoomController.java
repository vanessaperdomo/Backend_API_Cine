package com.sena.cinema.controller;

import com.sena.cinema.dto.request.ScreeningRoomRequest;
import com.sena.cinema.dto.response.ScreeningRoomResponse;
import com.sena.cinema.service.ScreeningRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/screening-rooms")
@RequiredArgsConstructor
public class ScreeningRoomController {

    private final ScreeningRoomService screeningRoomService;

    @PostMapping
    public ResponseEntity<ScreeningRoomResponse> create(
            @Valid @RequestBody ScreeningRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(screeningRoomService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ScreeningRoomResponse>> findAll() {
        return ResponseEntity.ok(screeningRoomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreeningRoomResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(screeningRoomService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreeningRoomResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody ScreeningRoomRequest request) {
        return ResponseEntity.ok(screeningRoomService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        screeningRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}