package be.kdg.ipj3.tictactoebackend.domain;

import java.util.UUID;

public record PlayerId(UUID id) {
    public PlayerId() {
        this(UUID.randomUUID());
    }
}
