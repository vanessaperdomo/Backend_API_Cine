package com.sena.cinema.service;

import com.sena.cinema.dto.request.ClientRequest;
import com.sena.cinema.dto.response.ClientResponse;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientResponse create(ClientRequest request);

    ClientResponse findById(UUID id);

    List<ClientResponse> findAll();

    ClientResponse update(UUID id, ClientRequest request);

    void delete(UUID id);
}