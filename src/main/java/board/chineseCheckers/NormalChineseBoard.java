package board.chineseCheckers;

import board.Field;

public class NormalChineseBoard extends ChineseCheckersBoard {

    public NormalChineseBoard() {
        board = new Field[17][25];
        cleanBoard();
        n = 4;
    }
}
