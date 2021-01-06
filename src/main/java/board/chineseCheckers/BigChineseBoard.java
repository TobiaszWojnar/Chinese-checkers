package board.chineseCheckers;

import board.Field;

public class BigChineseBoard extends ChineseCheckersBoard {

    public BigChineseBoard() {
        board = new Field[21][31];
        cleanBoard();
        this.n = 5;
    }
}
