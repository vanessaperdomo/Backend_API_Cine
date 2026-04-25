package com.sena.cinema.repository;

import com.sena.cinema.model.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, UUID> {

    boolean existsByRoomName(String roomName);

    boolean existsByRoomNameAndRoomIdNot(String roomName, UUID roomId);
}