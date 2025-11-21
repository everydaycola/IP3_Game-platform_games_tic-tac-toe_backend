package be.kdg.ipj3.tictactoebackend.api.dtos;

import java.util.UUID;

public record MoveDto(
        int x,
        int y,
        UUID player
) {}
