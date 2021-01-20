package com.javamaster.model;

import lombok.Data;

@Data
public class Game {

    private String gameId;
    private Player player1;//TODO list
    private Player player2;
    private GameStatus status;
    private int[][] board;
    private TicToe winner;//TODO field
    //TODO skipped
    //TODO resigned
    //TODO ruleSet

}
