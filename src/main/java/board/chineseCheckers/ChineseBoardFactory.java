package board.chineseCheckers;

/**
 * Factory of {@link ChineseCheckersBoard} of different sizes
 */
public class ChineseBoardFactory {

    /**
     * Factory method
     * @param board board type (size)
     * @return new board of given type
     */
    public ChineseCheckersBoard getBoard(String board) {
        if (board == null) {
            return null;
        }
        if (board.equalsIgnoreCase("small")) {
            return new SmallChineseBoard();
        }
        if (board.equalsIgnoreCase("normal")) {
            return new NormalChineseBoard();
        }
        if (board.equalsIgnoreCase("big")) {
            return new BigChineseBoard();
        }
        return null;
    }
}
