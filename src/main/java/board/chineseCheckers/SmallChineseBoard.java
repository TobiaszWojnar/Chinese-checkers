package board.chineseCheckers;

import board.Field;

public class SmallChineseBoard extends ChineseCheckersBoard {
    public SmallChineseBoard() {
        board = new Field[13][19];
        cleanBoard();
        this.n = 3;
    }
}
