package be.kdg.ipj3.tictactoebackend.api.dtos;

import java.util.UUID;

public record NewGameRequestDto(UUID player1Id, UUID player2Id) {}
