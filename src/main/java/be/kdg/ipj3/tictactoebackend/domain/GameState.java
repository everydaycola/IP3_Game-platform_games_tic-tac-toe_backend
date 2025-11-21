package be.kdg.ipj3.tictactoebackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class GameState {
    private final GameStateId id;
    private final Cell[][] state;
    private CharacterId playerOne;
    private CharacterId playerTwo;
    private Boolean isPlayerOneTurn;

    public GameState(CharacterId playerOne, CharacterId playerTwo) {
        this.id = GameStateId.create();
        this.state = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.state[i][j] = Cell.EMPTY;
            }
        }
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        // hardcoded, player 1 always starts
        this.isPlayerOneTurn = true;
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

    public void makeMove(int x, int y, Cell symbol) {
        if (this.state[x][y] != Cell.EMPTY) {
            throw new IllegalStateException("This cell is already occupied");
        }
        this.state[x][y] = symbol;
        this.isPlayerOneTurn = !this.isPlayerOneTurn;
    }

    public void checkTurn(CharacterId playerId) {
        if (this.isPlayerOneTurn ? !this.playerOne.equals(playerId) : !this.playerTwo.equals(playerId)) {
            throw new IllegalStateException("It is not your turn");
        }
    }

    public Cell getSymbol(CharacterId playerId) {
        if (this.playerOne.equals(playerId)) {
            return Cell.X;
        } else if (this.playerTwo.equals(playerId)) {
            return Cell.O;
        } else {
            throw new IllegalArgumentException("You are not in the game");
        }
    }
}
