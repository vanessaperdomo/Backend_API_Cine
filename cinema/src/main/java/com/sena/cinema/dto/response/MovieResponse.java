package com.sena.cinema.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class MovieResponse {

    private UUID movieId;
    private String title;
    private String genre;
    private String director;
    private Short releaseYear;
    private Short durationMin;
    private Short stock;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}