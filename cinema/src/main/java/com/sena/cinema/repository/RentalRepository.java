package com.sena.cinema.repository;

import com.sena.cinema.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {

    List<Rental> findByClient_ClientId(UUID clientId);

    List<Rental> findByMovie_MovieId(UUID movieId);

    List<Rental> findByStatus(String status);
}