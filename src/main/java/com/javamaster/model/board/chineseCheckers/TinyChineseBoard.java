package com.javamaster.model.board.chineseCheckers;

import com.javamaster.model.Field;

public class TinyChineseBoard extends ChineseCheckersBoard {
    public TinyChineseBoard() {
        board = new Field[9][13];
        cleanBoard();
        n = 2;
    }
}
