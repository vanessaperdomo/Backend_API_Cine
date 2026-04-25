package com.sena.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "movies", schema = "cine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movie_id", updatable = false, nullable = false)
    private UUID movieId;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "genre", nullable = false, length = 100)
    private String genre;

    @Column(name = "director", nullable = false, length = 150)
    private String director;

    @Column(name = "release_year", nullable = false)
    private Short releaseYear;

    @Column(name = "duration_min", nullable = false)
    private Short durationMin;

    @Column(name = "stock", nullable = false)
    private Short stock;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    
}