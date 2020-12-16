package server.chineseCheckers.logic;

import board.Board;
import board.Field;
import server.chineseCheckers.datastructures.CornerMap;

// TODO implement swapping when conditions met and implement isWinner
public class LogicUnitCanSwap extends LogicUnitAbstract {

    public LogicUnitCanSwap(Board board, CornerMap corners) {
        super(board, corners);
    }

    @Override
    protected void setPossible(int x, int y) {
        super.setPossible(x, y);
    }

    @Override
    protected void setPossibleJump(int x_mid, int y_mid, int x, int y) {
        super.setPossibleJump(x_mid, y_mid, x, y);
    }

    @Override
    protected void highlightJumpsRecursive(int x, int y) {
        super.highlightJumpsRecursive(x, y);
    }

    @Override
    public void highlightPossible(int x, int y, Field player) {
        super.highlightPossible(x, y, player);
    }

    @Override
    public void deselect(int x, int y, Field player) {
        super.deselect(x, y, player);
    }

    @Override
    public void move(int x, int y, int chosen_x, int chosen_y, Field player) {
        super.move(x, y, chosen_x, chosen_y, player);
    }

    @Override
    public boolean isWinner(Field player) {
        return false;
    }
}
