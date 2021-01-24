package com.javamaster.model.board.chineseCheckers;

/**
 * Factory of {@link ChineseCheckersBoard} of different sizes
 */
public class ChineseBoardFactory {

    /**
     * Factory method
     *
     * @param board board type (size)
     * @return new board of given type
     */
    public ChineseCheckersBoard getBoard(String board) {
        if (board == null) {
            return null;
        }
        if ("small".equalsIgnoreCase(board)) {
            return new SmallChineseBoard();
        }
        if ("normal".equalsIgnoreCase(board)) {
            return new NormalChineseBoard();
        }
        if ("big".equalsIgnoreCase(board)) {
            return new BigChineseBoard();
        }
        return null;
    }
}
