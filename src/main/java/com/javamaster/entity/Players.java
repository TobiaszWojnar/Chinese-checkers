package com.javamaster.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="players")
public class Players {

    @Id
    @Column(name="player_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int player_id;

    @Column(name = "login")
    private String login;

    @ManyToMany(cascade = {CascadeType.MERGE}, mappedBy = "players")
    private Set<Games> games = new HashSet<>();

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Games> getGames() {
        return games;
    }

    public void setGames(Set<Games> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "PlayerDB{" +
                "player_id=" + player_id +
                ", login='" + login + '\'' +
                '}';
    }
}
