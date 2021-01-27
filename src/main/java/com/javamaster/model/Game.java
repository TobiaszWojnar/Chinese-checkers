package com.javamaster.model;

import com.javamaster.model.chineseCheckers.datastructures.PlayerList;
import lombok.Data;

import java.util.List;

@Data
public class Game {

    private String gameId;
    private Field player;
    private PlayerList players;
    private List<Player> playerList;
    private GameStatus status;
    private Field[][] board;
    private List<Player> winners;
    private Player resigned;
    private Player skipped;
    private Field current;
}
