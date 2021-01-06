package server.chineseCheckers.logic;

import board.Field;
import board.IntPoint;
import board.chineseCheckers.ChineseCheckersBoard;
import board.chineseCheckers.corners.Corner;
import server.chineseCheckers.datastructures.CornerMap;

public class LogicUnitAllFilled extends LogicUnitAbstract {

    public LogicUnitAllFilled(ChineseCheckersBoard board, CornerMap corners) {
        super(board, corners);
    }

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