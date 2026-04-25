package com.sena.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "screening_rooms", schema = "cine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_id", updatable = false, nullable = false)
    private UUID roomId;

    @Column(name = "room_name", nullable = false, length = 100, unique = true)
    private String roomName;

    @Column(name = "capacity", nullable = false)
    private Short capacity;

    @Column(name = "room_type", nullable = false, length = 20)
    private String roomType;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (roomType == null) {
            roomType = "standard";
        }
    }
}