package game.server.chineseCheckers.logic;

import game.board.Field;
import game.board.chineseCheckers.ChineseCheckersBoard;
import game.server.chineseCheckers.datastructures.CornerMap;

// TODO implement isWinner
public class LogicUnitAtLeastOneFilled extends LogicUnitAbstract {

    public LogicUnitAtLeastOneFilled(ChineseCheckersBoard board, CornerMap corners) {
        super(board, corners);
    }

    @Override
    public boolean isWinner(Field player) {
        return false;
    }
}
