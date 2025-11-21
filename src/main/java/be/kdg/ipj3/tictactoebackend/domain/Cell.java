package be.kdg.ipj3.tictactoebackend.domain;

import lombok.Getter;

@Getter public enum Cell {
    EMPTY('_', (byte) 0),
    X('X', (byte) 1),
    O('O', (byte) -1);

    private final char name;
    private final byte aiValue;

    Cell(char name, byte aiValue) {
        this.name = name;
        this.aiValue = aiValue;
    }

}
