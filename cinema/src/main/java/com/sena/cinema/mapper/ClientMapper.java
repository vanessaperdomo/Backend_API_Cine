package com.sena.cinema.mapper;

import com.sena.cinema.dto.request.ClientRequest;
import com.sena.cinema.dto.response.ClientResponse;
import com.sena.cinema.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequest request) {
        return Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .clientId(client.getClientId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registeredAt(client.getRegisteredAt())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }

    public void updateEntity(Client client, ClientRequest request) {
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
    }
}