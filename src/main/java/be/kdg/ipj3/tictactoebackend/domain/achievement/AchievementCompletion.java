package be.kdg.ipj3.tictactoebackend.domain.achievement;

import be.kdg.ipj3.tictactoebackend.domain.PlayerId;

import java.time.LocalDateTime;

public class AchievementCompletion {
    private final AchievementId achievementId;
    private final PlayerId playerId;
    private LocalDateTime completionTime;

    public AchievementCompletion(LocalDateTime completionTime, PlayerId playerId, AchievementId achievementId) {
        this.completionTime = completionTime;
        this.playerId = playerId;
        this.achievementId = achievementId;
    }
}
