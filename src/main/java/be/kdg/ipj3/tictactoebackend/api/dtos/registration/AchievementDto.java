package be.kdg.ipj3.tictactoebackend.api.dtos.registration;

import java.util.UUID;

public record AchievementDto(UUID id, String name, String description) {
}
