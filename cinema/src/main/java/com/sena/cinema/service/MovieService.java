package com.sena.cinema.service;

import com.sena.cinema.dto.request.MovieRequest;
import com.sena.cinema.dto.response.MovieResponse;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    MovieResponse create(MovieRequest request);

    MovieResponse findById(UUID id);

    List<MovieResponse> findAll();

    MovieResponse update(UUID id, MovieRequest request);

    void delete(UUID id);
}