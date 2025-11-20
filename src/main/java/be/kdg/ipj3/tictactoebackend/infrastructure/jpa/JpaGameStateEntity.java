package be.kdg.ipj3.tictactoebackend.infrastructure.jpa;

import be.kdg.ipj3.tictactoebackend.domain.Cell;
import be.kdg.ipj3.tictactoebackend.domain.GameState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Entity
@Table(name = "game_state")
public class JpaGameStateEntity {
    @Getter
    @Id
    private UUID id;

    @Getter
    @Column
    @Convert(converter = BoardConverter.class)
    private Cell[][] state;

    protected JpaGameStateEntity() {
    }

    public static JpaGameStateEntity fromDomain(GameState gameState) {
        return new JpaGameStateEntity(
                gameState.getId().id(),
                gameState.getState()
        );
    }

    public JpaGameStateEntity toDomain() {
        return new JpaGameStateEntity(
                this.id,
                this.state
        );
    }
}
