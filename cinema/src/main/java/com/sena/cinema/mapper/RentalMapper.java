package com.sena.cinema.mapper;

import com.sena.cinema.dto.request.RentalRequest;
import com.sena.cinema.dto.response.RentalResponse;
import com.sena.cinema.model.Client;
import com.sena.cinema.model.Movie;
import com.sena.cinema.model.Rental;
import com.sena.cinema.model.ScreeningRoom;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public Rental toEntity(RentalRequest request, Client client,
                           Movie movie, ScreeningRoom room) {
        return Rental.builder()
                .client(client)
                .movie(movie)
                .room(room)
                .rentalDate(request.getRentalDate())
                .returnDate(request.getReturnDate())
                .status(request.getStatus() != null ? request.getStatus() : "active")
                .build();
    }

    public RentalResponse toResponse(Rental rental) {
        return RentalResponse.builder()
                .rentalId(rental.getRentalId())
                .clientId(rental.getClient().getClientId())
                .clientFullName(rental.getClient().getFirstName()
                        + " " + rental.getClient().getLastName())
                .movieId(rental.getMovie().getMovieId())
                .movieTitle(rental.getMovie().getTitle())
                .roomId(rental.getRoom().getRoomId())
                .roomName(rental.getRoom().getRoomName())
                .rentalDate(rental.getRentalDate())
                .returnDate(rental.getReturnDate())
                .status(rental.getStatus())
                .createdAt(rental.getCreatedAt())
                .updatedAt(rental.getUpdatedAt())
                .build();
    }

    public void updateEntity(Rental rental, RentalRequest request,
                             Client client, Movie movie, ScreeningRoom room) {
        rental.setClient(client);
        rental.setMovie(movie);
        rental.setRoom(room);
        rental.setRentalDate(request.getRentalDate() != null
                ? request.getRentalDate() : rental.getRentalDate());
        rental.setReturnDate(request.getReturnDate());
        rental.setStatus(request.getStatus() != null
                ? request.getStatus() : rental.getStatus());
    }
}