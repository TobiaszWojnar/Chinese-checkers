package com.javamaster.model;

import com.javamaster.model.chineseCheckers.datastructures.PlayerList;
import lombok.Data;

@Data
public class Game {

    private String gameId;
    private PlayerList players;
    private GameStatus status;
    private Field[][] board;
    private Player winner;
    private Player resigned;
    private Player skipped;
    private Field current;
}
