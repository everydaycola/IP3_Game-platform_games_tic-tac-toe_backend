package be.kdg.ipj3.tictactoebackend.api.dtos;

import be.kdg.ipj3.tictactoebackend.domain.GameState;

public record GameStateDtoAI(
        Integer[][] board
) {
    public static GameStateDtoAI from(GameState gameState) {
        return new GameStateDtoAI(gameState.getGameStateForAI());
    }
}
