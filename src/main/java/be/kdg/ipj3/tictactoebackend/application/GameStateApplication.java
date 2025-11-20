package be.kdg.ipj3.tictactoebackend.application;

import be.kdg.ipj3.tictactoebackend.domain.GameState;
import be.kdg.ipj3.tictactoebackend.domain.GameStateRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class GameStateApplication {

    private final GameStateRepository gameStateRepository;

    public GameStateApplication(GameStateRepository gameStateRepository1) {
        this.gameStateRepository = gameStateRepository1;
    }

    public GameState createBoard() {
        final var board = new GameState();
        gameStateRepository.save(board);
        return board;
    }


}
