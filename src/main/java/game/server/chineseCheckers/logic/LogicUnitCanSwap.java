package game.server.chineseCheckers.logic;

import game.board.Field;
import game.board.chineseCheckers.ChineseCheckersBoard;
import game.server.chineseCheckers.datastructures.CornerMap;


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
