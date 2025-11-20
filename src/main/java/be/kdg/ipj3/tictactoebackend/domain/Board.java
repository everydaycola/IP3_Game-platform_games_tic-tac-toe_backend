package be.kdg.ipj3.tictactoebackend.domain;

public class Board {
    private final Cell[][] state;

    private PlayerId atTurn;

    public Board() {
        this.state = new Cell[3][3];
    }

    public void place(int x, int y, Cell cell) {
        if (state[x][y] != Cell.Empty) {
            throw new IllegalStateException("Cell is not empty");
        }
        if (cell == Cell.Empty) {
            throw new IllegalArgumentException("You cannot place an empty cell");
        }
        state[x][y] = cell;
    }
}
