package com.javamaster.model.chineseCheckers.logic;

import com.javamaster.model.Field;
import com.javamaster.model.board.chineseCheckers.ChineseCheckersBoard;
import com.javamaster.model.chineseCheckers.datastructures.CornerMap;

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
