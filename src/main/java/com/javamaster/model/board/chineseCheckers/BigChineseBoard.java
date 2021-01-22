package com.javamaster.model.board.chineseCheckers;


import com.javamaster.model.Field;

/**
 * Chinese Checkers Board of size 5
 */
public class BigChineseBoard extends ChineseCheckersBoard {

    /**
     * Constructor
     */
    public BigChineseBoard() {
        board = new Field[21][31];
        cleanBoard();
        n = 5;
    }
}
