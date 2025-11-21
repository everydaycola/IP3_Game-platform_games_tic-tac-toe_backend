package be.kdg.ipj3.tictactoebackend.api.dtos;

import be.kdg.ipj3.tictactoebackend.domain.GameState;

import java.util.UUID;

public record GameStateDtoChar(
        UUID id,
        Character[][] board,
        UUID player1,
        UUID player2,
        boolean atTurn
) {
    public static GameStateDtoChar from(GameState gameState) {
        return new GameStateDtoChar(
                gameState.getId().id(),
                gameState.getGameStateAsStrings(),
                gameState.getPlayerOne().id(),
                gameState.getPlayerTwo().id(),
                gameState.getIsPlayerOneTurn()
        );
    }
}
