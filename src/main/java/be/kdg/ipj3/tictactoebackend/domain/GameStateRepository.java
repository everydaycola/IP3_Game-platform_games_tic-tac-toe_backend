package be.kdg.ipj3.tictactoebackend.domain;

import java.util.Optional;

public interface GameStateRepository {
    void save(GameState gameState);

    Optional<GameState> get(GameStateId stateId);
}
