package game.board.chineseCheckers;

import game.board.Field;

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
