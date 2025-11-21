package be.kdg.ipj3.tictactoebackend.api.dtos;

import java.util.UUID;

public record NewMatchDto (
    UUID player1,
    UUID player2
){}
