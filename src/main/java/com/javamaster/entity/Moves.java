package com.javamaster.entity;

import javax.persistence.*;
import java.beans.ConstructorProperties;

@Entity
@Table(name = "moves")
@IdClass(MoveId.class)
public class Moves {

    @Id
    @Column(name = "game")
    private String game;

    @Id
    @Column(name = "move_id")
    private int move_id;

    @Column(name = "player")
    private int player;

    @Column(name = "from_x")
    private int from_x;
    @Column(name = "from_y")
    private int from_y;
    @Column(name = "to_x")
    private int to_x;
    @Column(name = "to_y")
    private int to_y;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "game", insertable = false, updatable = false)
    private Games games;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getMove_id() {
        return move_id;
    }

    public void setMove_id(int move_id) {
        this.move_id = move_id;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getFrom_x() {
        return from_x;
    }

    public void setFrom_x(int from_x) {
        this.from_x = from_x;
    }

    public int getFrom_y() {
        return from_y;
    }

    public void setFrom_y(int from_y) {
        this.from_y = from_y;
    }

    public int getTo_x() {
        return to_x;
    }

    public void setTo_x(int to_x) {
        this.to_x = to_x;
    }

    public int getTo_y() {
        return to_y;
    }

    public void setTo_y(int to_y) {
        this.to_y = to_y;
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Moves{" +
                "game='" + game + '\'' +
                ", move_id=" + move_id +
                ", player='" + player + '\'' +
                ", from_x=" + from_x +
                ", from_y=" + from_y +
                ", to_x=" + to_x +
                ", to_y=" + to_y +
                '}';
    }
}
