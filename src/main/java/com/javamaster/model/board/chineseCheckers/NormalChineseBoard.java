package com.javamaster.model.board.chineseCheckers;


import com.javamaster.model.Field;

/**
 * Chinese Checkers Board of size 4
 */
public class NormalChineseBoard extends ChineseCheckersBoard {

    /**
     * Constructor
     */
    public NormalChineseBoard() {
        board = new Field[17][25];
        cleanBoard();
        n = 4;
    }
}
