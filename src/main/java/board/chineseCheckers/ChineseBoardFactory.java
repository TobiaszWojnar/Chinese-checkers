package board.chineseCheckers;

public class ChineseBoardFactory {

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
