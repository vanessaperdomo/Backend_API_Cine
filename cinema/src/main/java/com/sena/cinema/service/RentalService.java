package com.sena.cinema.service;

import com.sena.cinema.dto.request.RentalRequest;
import com.sena.cinema.dto.response.RentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {

    RentalResponse create(RentalRequest request);

    RentalResponse findById(UUID id);

    List<RentalResponse> findAll();

    RentalResponse update(UUID id, RentalRequest request);

    void delete(UUID id);
}