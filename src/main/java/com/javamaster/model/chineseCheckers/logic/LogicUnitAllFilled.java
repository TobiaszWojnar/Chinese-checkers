package com.javamaster.model.chineseCheckers.logic;

import com.javamaster.model.Field;
import com.javamaster.model.board.IntPoint;
import com.javamaster.model.board.chineseCheckers.ChineseCheckersBoard;
import com.javamaster.model.board.chineseCheckers.corners.Corner;
import com.javamaster.model.chineseCheckers.datastructures.CornerMap;

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