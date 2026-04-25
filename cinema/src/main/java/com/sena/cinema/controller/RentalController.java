package com.sena.cinema.controller;

import com.sena.cinema.dto.request.RentalRequest;
import com.sena.cinema.dto.response.RentalResponse;
import com.sena.cinema.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalResponse> create(
            @Valid @RequestBody RentalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rentalService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<RentalResponse>> findAll() {
        return ResponseEntity.ok(rentalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody RentalRequest request) {
        return ResponseEntity.ok(rentalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        rentalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}