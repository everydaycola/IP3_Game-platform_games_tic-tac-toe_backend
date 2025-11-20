package be.kdg.ipj3.tictactoebackend.domain;

public class User implements Player {
    private PlayerId id;
    private String name;
    private String icon;
    private String email;

    public User(PlayerId id, String name, String icon, String email) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.email = email;
    }
}
