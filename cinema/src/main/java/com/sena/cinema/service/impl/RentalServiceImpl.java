package com.sena.cinema.service.impl;

import com.sena.cinema.dto.request.RentalRequest;
import com.sena.cinema.dto.response.RentalResponse;
import com.sena.cinema.exception.BadRequestException;
import com.sena.cinema.exception.ResourceNotFoundException;
import com.sena.cinema.mapper.RentalMapper;
import com.sena.cinema.model.Client;
import com.sena.cinema.model.Movie;
import com.sena.cinema.model.Rental;
import com.sena.cinema.model.ScreeningRoom;
import com.sena.cinema.repository.ClientRepository;
import com.sena.cinema.repository.MovieRepository;
import com.sena.cinema.repository.RentalRepository;
import com.sena.cinema.repository.ScreeningRoomRepository;
import com.sena.cinema.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final ClientRepository clientRepository;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final RentalMapper rentalMapper;

    @Override
    @Transactional
    public RentalResponse create(RentalRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Cliente no encontrado con id: " + request.getClientId()
                ));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Película no encontrada con id: " + request.getMovieId()
                ));

        // Verificar que haya stock disponible
        if (movie.getStock() <= 0) {
            throw new BadRequestException(
                "La película '" + movie.getTitle() + "' no tiene stock disponible"
            );
        }

        ScreeningRoom room = screeningRoomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Sala no encontrada con id: " + request.getRoomId()
                ));

        // Validar fechas si se envían ambas
        if (request.getRentalDate() != null && request.getReturnDate() != null
                && request.getReturnDate().isBefore(request.getRentalDate())) {
            throw new BadRequestException(
                "La fecha de devolución no puede ser anterior a la fecha de renta"
            );
        }

        // Descontar stock
        movie.setStock((short) (movie.getStock() - 1));
        movieRepository.save(movie);

        Rental rental = rentalMapper.toEntity(request, client, movie, room);
        return rentalMapper.toResponse(rentalRepository.save(rental));
    }

    @Override
    @Transactional(readOnly = true)
    public RentalResponse findById(UUID id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Renta no encontrada con id: " + id
                ));
        return rentalMapper.toResponse(rental);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalResponse> findAll() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public RentalResponse update(UUID id, RentalRequest request) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Renta no encontrada con id: " + id
                ));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Cliente no encontrado con id: " + request.getClientId()
                ));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Película no encontrada con id: " + request.getMovieId()
                ));

        ScreeningRoom room = screeningRoomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Sala no encontrada con id: " + request.getRoomId()
                ));

        // Si cambia el status a "returned", devolver stock
        if ("returned".equals(request.getStatus())
                && !"returned".equals(rental.getStatus())) {
            movie.setStock((short) (movie.getStock() + 1));
            movieRepository.save(movie);
        }

        // Validar fechas
        if (request.getRentalDate() != null && request.getReturnDate() != null
                && request.getReturnDate().isBefore(request.getRentalDate())) {
            throw new BadRequestException(
                "La fecha de devolución no puede ser anterior a la fecha de renta"
            );
        }

        rentalMapper.updateEntity(rental, request, client, movie, room);
        return rentalMapper.toResponse(rentalRepository.save(rental));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Renta no encontrada con id: " + id
                ));

        // Si la renta estaba activa, devolver el stock
        if ("active".equals(rental.getStatus())) {
            Movie movie = rental.getMovie();
            movie.setStock((short) (movie.getStock() + 1));
            movieRepository.save(movie);
        }

        rentalRepository.deleteById(id);
    }
}