package be.kdg.ipj3.tictactoebackend.domain;

import lombok.Getter;

@Getter
public class Player extends GameCharacter {
    private final String email;

    public Player(CharacterId id, String name, String icon, String email) {
        super(id, name, icon);
        this.email = email;
    }
}
