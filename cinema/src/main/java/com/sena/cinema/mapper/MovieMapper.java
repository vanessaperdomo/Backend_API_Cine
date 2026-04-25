package com.sena.cinema.mapper;

import com.sena.cinema.dto.request.MovieRequest;
import com.sena.cinema.dto.response.MovieResponse;
import com.sena.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toEntity(MovieRequest request) {
        return Movie.builder()
                .title(request.getTitle())
                .genre(request.getGenre())
                .director(request.getDirector())
                .releaseYear(request.getReleaseYear())
                .durationMin(request.getDurationMin())
                .stock(request.getStock())
                .build();
    }

    public MovieResponse toResponse(Movie movie) {
        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .durationMin(movie.getDurationMin())
                .stock(movie.getStock())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }

    public void updateEntity(Movie movie, MovieRequest request) {
        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());
        movie.setDirector(request.getDirector());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setDurationMin(request.getDurationMin());
        movie.setStock(request.getStock());
    }
}