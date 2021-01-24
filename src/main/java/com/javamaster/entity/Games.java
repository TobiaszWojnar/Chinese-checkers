package com.javamaster.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Games {

    @Id
    @Column(name = "game_id")
    private String game_id;

    @Column(name = "numOfPlayers")
    private int numOfPlayers;

    @Column(name = "variant")
    private int variant;

    @Column(name = "boardType")
    private String boardType;

    @Column(name = "starting_player")
    private int starting_player;

    @Column(name = "began")
    private Timestamp began;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "games_players",
            joinColumns = {@JoinColumn(name = "game")},
            inverseJoinColumns = {@JoinColumn(name = "player")}
    )
    Set<Players> players = new HashSet<>();

    public String getGame_id() {
        return game_id;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "games")
    private Set<Moves> moves;

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public int getStarting_player() {
        return starting_player;
    }

    public void setStarting_player(int starting_player) {
        this.starting_player = starting_player;
    }

    public Timestamp getBegan() {
        return began;
    }

    public void setBegan(Timestamp began) {
        this.began = began;
    }

    public Set<Players> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Players> players) {
        this.players = players;
    }

    public Set<Moves> getMoves() {
        return moves;
    }

    public void setMoves(Set<Moves> moves) {
        this.moves = moves;
    }

    public void addMove(Moves move) {
        moves.add(move);
    }

    @Override
    public String toString() {
        return "Games{" +
                "game_id='" + game_id + '\'' +
                ", numOfPlayers=" + numOfPlayers +
                ", variant=" + variant +
                ", boardType='" + boardType + '\'' +
                ", starting_player=" + starting_player +
                ", began=" + began +
                '}';
    }
}
