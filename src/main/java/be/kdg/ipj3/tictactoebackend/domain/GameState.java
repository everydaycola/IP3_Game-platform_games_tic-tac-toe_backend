package be.kdg.ipj3.tictactoebackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class GameState {
    private final GameStateId id;
    private final Cell[][] state;
    private CharacterId player1;
    private CharacterId player2;
    private CharacterId atTurn;

    public GameState(CharacterId player1, CharacterId player2) {
        this.id = GameStateId.create();
        this.state = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.state[i][j] = Cell.EMPTY;
            }
        }
        this.player1 = player1;
        this.player2 = player2;
        // hardcoded, player 1 always starts
        this.atTurn = player1;
    }

    public void place(int x, int y, Cell cell) {
        if (state[x][y] != Cell.EMPTY) {
            throw new IllegalStateException("Cell is not empty");
        }
        if (cell == Cell.EMPTY) {
            throw new IllegalArgumentException("You cannot place an empty cell");
        }
        state[x][y] = cell;
    }

    public Integer[][] getGameStateForAI() {
        return Arrays.stream(this.state)
                .map(row -> Arrays.stream(row)
                        .map(Cell::getAiValue)
                        .toArray(Integer[]::new)
                )
                .toArray(Integer[][]::new);
    }

    public Character[][] getGameStateAsStrings() {
        return Arrays.stream(this.state)
                .map(row -> Arrays.stream(row)
                        .map(Cell::getName)
                        .toArray(Character[]::new)
                )
                .toArray(Character[][]::new);
    }

}
