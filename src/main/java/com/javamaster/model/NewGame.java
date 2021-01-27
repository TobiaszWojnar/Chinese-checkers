package com.javamaster.model;

import lombok.Data;

@Data
public class NewGame {
    private Player player;
    private int numOfPlayers;
    private int ruleSet;
    private String boardType;
}
