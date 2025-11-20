package be.kdg.ipj3.tictactoebackend.domain;

public class User implements Player {
    private PlayerId id;
    private String name;
    private String Icon;
    private String email;

    public User(PlayerId id, String name, String icon, String email) {
        this.id = id;
        this.name = name;
        Icon = icon;
        this.email = email;
    }
}
