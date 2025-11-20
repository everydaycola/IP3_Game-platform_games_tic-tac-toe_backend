package be.kdg.ipj3.tictactoebackend.api.dtos;

import be.kdg.ipj3.tictactoebackend.domain.GameState;

public record GameStateDtoChar(
        Character[][] board
) {
    public static GameStateDtoChar from(GameState gameState) {
        return new GameStateDtoChar(gameState.getGameStateAsStrings());
    }
}
