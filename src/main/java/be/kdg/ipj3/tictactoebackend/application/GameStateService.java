package be.kdg.ipj3.tictactoebackend.application;

import be.kdg.ipj3.tictactoebackend.domain.*;
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
        log.info("Creating new game board, match between {} and {}", player1.id(), player2.id());
        final var board = new GameState(player1, player2);
        gameStateRepository.save(board);
        return board;
    }

    public GameState getState(GameStateId stateId) {
        log.info("Getting game state for {}", stateId.id());
        return gameStateRepository.get(stateId).orElseThrow(stateId::notFound);
    }

    public GameState makeMove(int x, int y, CharacterId playerId, GameStateId stateId) {
        log.info("Making move at ({}, {}) by player {}", x, y, playerId.id());
        final var board = gameStateRepository.get(stateId).orElseThrow(stateId::notFound);
        final var symbol = board.getSymbol(playerId);
        board.checkTurn(playerId);
        board.makeMove(x, y, symbol);
        gameStateRepository.save(board);
        return board;
    }
}
