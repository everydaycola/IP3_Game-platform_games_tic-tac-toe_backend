package be.kdg.ipj3.tictactoebackend.infrastructure.gameState.ai.dtos;

import be.kdg.ipj3.tictactoebackend.domain.GameState;

import java.util.UUID;

public record GameStateDtoAI(UUID id, Integer[][] board) {
    public static GameStateDtoAI from(GameState gameState) {
        return new GameStateDtoAI(gameState.getId().id(), gameState.getGameStateForAI());
    }
}
