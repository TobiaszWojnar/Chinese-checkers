package server.chineseCheckers.logic;

import board.Board;
import board.Field;
import board.chineseCheckers.ChineseCheckersBoard;
import server.chineseCheckers.datastructures.CornerMap;


// TODO implement swapping when conditions met and implement isWinner
public class LogicUnitCanSwap extends LogicUnitAbstract {

    public LogicUnitCanSwap(ChineseCheckersBoard board, CornerMap corners) {
        super(board, corners);
    }


    @Override
    public boolean isWinner(Field player) {
        return false;
    }
}
