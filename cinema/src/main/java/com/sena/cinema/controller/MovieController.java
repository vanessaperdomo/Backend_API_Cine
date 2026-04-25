package com.sena.cinema.controller;

import com.sena.cinema.dto.request.MovieRequest;
import com.sena.cinema.dto.response.MovieResponse;
import com.sena.cinema.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable UUID id,
                                                 @Valid @RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}