package be.kdg.ipj3.tictactoebackend.application;

import be.kdg.ipj3.tictactoebackend.domain.GameState;
import be.kdg.ipj3.tictactoebackend.domain.GameStateId;
import be.kdg.ipj3.tictactoebackend.domain.GameStateRepository;
import be.kdg.ipj3.tictactoebackend.domain.CharacterId;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class GameStateService {

    private final GameStateRepository gameStateRepository;

    public GameStateService(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public GameState createBoard(CharacterId player1, CharacterId player2) {
        final var board = new GameState(player1, player2);
        gameStateRepository.save(board);
        return board;
    }

    public GameState getState(GameStateId stateId) {
        return gameStateRepository.get(stateId).orElseThrow(stateId::notFound);
    }
}
