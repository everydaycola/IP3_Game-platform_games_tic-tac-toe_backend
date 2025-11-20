package be.kdg.ipj3.tictactoebackend.domain;

public class User implements Player {
    private String email;

    public User(String email) {
        this.email = email;
    }
}
