package server.chineseCheckers.logic;

import board.Board;
import board.Field;
import board.IntPoint;
import board.corners.Corner;
import server.chineseCheckers.datastructures.CornerMap;

public class LogicUnitAllFilled extends LogicUnitAbstract {

    public LogicUnitAllFilled(Board board, CornerMap corners) {
        super(board, corners);
    }

    /*protected void setPossible(int x, int y) {
        if (board.isValidField(x, y) && board.isEmpty(x, y))
            board.setField(x, y, Field.Possible);
    }

    protected void setPossibleJump(int x_mid, int y_mid, int x, int y) {
        if (board.isValidField(x, y)
                && board.isEmpty(x, y)
                && board.hasPawn(x_mid, y_mid)
                && !visited[x][y]) {
            visited[x][y] = true;
            board.setField(x, y, Field.Possible);
            highlightJumpsRecursive(x, y);
        }
    }

    protected void highlightJumpsRecursive(int x, int y) {
        setPossibleJump(x - 1, y + 1, x - 2, y + 2);
        setPossibleJump(x + 1, y + 1, x + 2, y + 2);
        setPossibleJump(x - 1, y - 1, x - 2, y - 2);
        setPossibleJump(x + 1, y - 1, x + 2, y - 2);
        setPossibleJump(x - 2, y, x - 4, y);
        setPossibleJump(x + 2, y, x + 4, y);
    }

    public void highlightPossible(int x, int y, Field player) {
        if (board.getField(x, y) == player) {
            board.setField(x, y, Field.Chosen);
            setPossible(x - 1, y + 1);
            setPossible(x + 1, y + 1);
            setPossible(x - 1, y - 1);
            setPossible(x + 1, y - 1);
            setPossible(x - 2, y);
            setPossible(x + 2, y);
            for (boolean[] row : visited) {
                Arrays.fill(row, false);
            }
            highlightJumpsRecursive(x, y);
        }
    }

    public void deselect(int x, int y, Field player) {
        board.setField(x, y, player);
        for (int i = 0; i < board.getWidth(); i++)
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.getField(i, j) == Field.Possible) {
                    board.setField(i, j, Field.Empty);
                }
            }
    }

    public void move(int x, int y, int chosen_x, int chosen_y, Field player) {
        board.setField(chosen_x, chosen_y, Field.Empty);
        deselect(x, y, player);
    }*/

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