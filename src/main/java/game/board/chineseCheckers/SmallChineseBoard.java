package game.board.chineseCheckers;

import game.board.Field;

/**
 * Chinese Checkers Board of size 3
 */
public class SmallChineseBoard extends ChineseCheckersBoard {

    /**
     * Constructor
     */
    public SmallChineseBoard() {
        board = new Field[13][19];
        cleanBoard();
        n = 3;
    }
}
