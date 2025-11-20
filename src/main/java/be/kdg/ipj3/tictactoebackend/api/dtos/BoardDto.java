package be.kdg.ipj3.tictactoebackend.api.dtos;

import be.kdg.ipj3.tictactoebackend.domain.Board;

import java.util.Arrays;

public record BoardDto(
        char[][] board
) {

    public static BoardDto from(Board board) {
        return new BoardDto(
                Arrays.stream(board.getState())
                        .map(row -> {
                            // We must create the char[] manually because Streams
                            // don't support 'char' primitives directly as well as 'int'
                            char[] charRow = new char[row.length];
                            for (int i = 0; i < row.length; i++) {
                                charRow[i] = row[i].getName();
                            }
                            return charRow;
                        })
                        .toArray(char[][]::new)
        );
    }
}
