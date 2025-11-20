package be.kdg.ipj3.tictactoebackend.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaGameStateRepository extends JpaRepository<JpaGameStateEntity, UUID> {
}
