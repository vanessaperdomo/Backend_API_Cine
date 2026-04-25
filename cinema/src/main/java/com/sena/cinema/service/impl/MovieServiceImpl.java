package com.sena.cinema.service.impl;

import com.sena.cinema.dto.request.MovieRequest;
import com.sena.cinema.dto.response.MovieResponse;
import com.sena.cinema.exception.DuplicateResourceException;
import com.sena.cinema.exception.ResourceNotFoundException;
import com.sena.cinema.mapper.MovieMapper;
import com.sena.cinema.model.Movie;
import com.sena.cinema.repository.MovieRepository;
import com.sena.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional
    public MovieResponse create(MovieRequest request) {
        if (movieRepository.existsByTitleAndReleaseYear(request.getTitle(), request.getReleaseYear())) {
            throw new DuplicateResourceException(
                "Ya existe una película con el título '" + request.getTitle() +
                "' del año " + request.getReleaseYear()
            );
        }
        Movie movie = movieMapper.toEntity(request);
        return movieMapper.toResponse(movieRepository.save(movie));
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponse findById(UUID id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con id: " + id));
        return movieMapper.toResponse(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public MovieResponse update(UUID id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con id: " + id));
        movieMapper.updateEntity(movie, request);
        return movieMapper.toResponse(movieRepository.save(movie));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Película no encontrada con id: " + id);
        }
        movieRepository.deleteById(id);
    }
}