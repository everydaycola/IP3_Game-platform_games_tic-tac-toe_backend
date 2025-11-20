package be.kdg.ipj3.tictactoebackend.infrastructure;

import be.kdg.ipj3.tictactoebackend.domain.GameState;
import be.kdg.ipj3.tictactoebackend.domain.GameStateRepository;
import be.kdg.ipj3.tictactoebackend.infrastructure.jpa.JpaGameStateEntity;
import be.kdg.ipj3.tictactoebackend.infrastructure.jpa.JpaGameStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DbGameStateRepository implements GameStateRepository {

    private final JpaGameStateRepository gameStateRepository;

    public DbGameStateRepository(JpaGameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    @Override
    public void save(GameState gameState) {
        log.info("Saving game state to database");
        gameStateRepository.save(JpaGameStateEntity.fromDomain(gameState));
    }
}
