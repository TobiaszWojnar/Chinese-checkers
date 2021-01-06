package server.chineseCheckers.logic;

import board.Board;
import board.Field;
import board.chineseCheckers.ChineseCheckersBoard;
import server.chineseCheckers.datastructures.CornerMap;

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
