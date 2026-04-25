package com.sena.cinema.service.impl;

import com.sena.cinema.dto.request.ClientRequest;
import com.sena.cinema.dto.response.ClientResponse;
import com.sena.cinema.exception.DuplicateResourceException;
import com.sena.cinema.exception.ResourceNotFoundException;
import com.sena.cinema.mapper.ClientMapper;
import com.sena.cinema.model.Client;
import com.sena.cinema.repository.ClientRepository;
import com.sena.cinema.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponse create(ClientRequest request) {
        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                "Ya existe un cliente con el email: " + request.getEmail()
            );
        }
        Client client = clientMapper.toEntity(request);
        return clientMapper.toResponse(clientRepository.save(client));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponse findById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Cliente no encontrado con id: " + id
                ));
        return clientMapper.toResponse(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public ClientResponse update(UUID id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Cliente no encontrado con id: " + id
                ));

        // Si cambió el email, verificar que no lo use otro cliente
        if (!client.getEmail().equalsIgnoreCase(request.getEmail())
                && clientRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                "El email " + request.getEmail() + " ya está en uso"
            );
        }

        clientMapper.updateEntity(client, request);
        return clientMapper.toResponse(clientRepository.save(client));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Cliente no encontrado con id: " + id
            );
        }
        clientRepository.deleteById(id);
    }
}