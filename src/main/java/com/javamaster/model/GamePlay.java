package com.javamaster.model;

import lombok.Data;

@Data
public class GamePlay {

    private TicToe type;//TODO filed
    private Integer coordinateX;
    private Integer coordinateY;
    private String gameId;
    //TODO string message
}
