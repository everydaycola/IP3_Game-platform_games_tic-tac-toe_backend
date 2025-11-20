package be.kdg.ipj3.tictactoebackend.domain;

import java.util.UUID;

public record GameStateId(UUID id) {
    public static GameStateId create() {
        return new GameStateId(UUID.randomUUID());
    }
}
