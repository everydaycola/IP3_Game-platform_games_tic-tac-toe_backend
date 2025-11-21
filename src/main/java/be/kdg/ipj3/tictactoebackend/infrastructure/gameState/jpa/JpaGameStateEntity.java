package be.kdg.ipj3.tictactoebackend.infrastructure.gameState.jpa;

import be.kdg.ipj3.tictactoebackend.domain.Cell;
import be.kdg.ipj3.tictactoebackend.domain.GameState;
import be.kdg.ipj3.tictactoebackend.domain.GameStateId;
import be.kdg.ipj3.tictactoebackend.domain.CharacterId;
import be.kdg.ipj3.tictactoebackend.infrastructure.user.jpa.JpaGameCharacterEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor @Entity @Getter @Table(name = "game_state") public class JpaGameStateEntity {
    @Id private UUID id;
    @Column @Convert(converter = BoardConverter.class) private Cell[][] state;
    @OneToOne() @JoinColumn(name = "player1_id")
    private JpaGameCharacterEntity player1;
    @OneToOne() @JoinColumn(name = "player2_id")
    private JpaGameCharacterEntity player2;
    @Column private UUID atTurn;

    protected JpaGameStateEntity() {
    }

    public static JpaGameStateEntity fromDomain(GameState gameState, JpaGameCharacterEntity player1, JpaGameCharacterEntity player2) {
        return new JpaGameStateEntity(
                gameState.getId().id(),
                gameState.getState(),
                player1,
                player2,
                gameState.getAtTurn().id()
        );
    }

    public GameState toDomain() {
        return new GameState(
                new GameStateId(this.id),
                this.state,
                new CharacterId(this.player1.getId()),
                new CharacterId(this.player2.getId()),
                new CharacterId(this.atTurn)
        );
    }
}
