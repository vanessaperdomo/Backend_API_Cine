package com.sena.cinema.repository;

import com.sena.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

    Optional<Movie> findByTitleAndReleaseYear(String title, Short releaseYear);

    boolean existsByTitleAndReleaseYear(String title, Short releaseYear);
}