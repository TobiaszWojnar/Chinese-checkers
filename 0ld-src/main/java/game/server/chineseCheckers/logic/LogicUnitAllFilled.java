package game.server.chineseCheckers.logic;

import game.board.Field;
import game.board.IntPoint;
import game.board.chineseCheckers.ChineseCheckersBoard;
import game.board.chineseCheckers.corners.Corner;
import game.server.chineseCheckers.datastructures.CornerMap;

/**
 * Logic unit for AllFilled rulset
 */
public class LogicUnitAllFilled extends LogicUnitAbstract {

    /**
     * {@inheritDoc}
     */
    public LogicUnitAllFilled(ChineseCheckersBoard board, CornerMap corners) {
        super(board, corners);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWinner(Field player) {
        Corner corner = corners.get(player);
        for (IntPoint point : corner.points) {
            if (!(board.getField(point.getX(), point.getY()) == player)) {
                return false;
            }
        }
        return true;
    }
}