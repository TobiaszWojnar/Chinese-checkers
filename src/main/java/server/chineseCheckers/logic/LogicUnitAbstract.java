package server.chineseCheckers.logic;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;
import board.IntPoint;
import board.corners.Corner;
import server.chineseCheckers.datastructures.CornerMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class LogicUnitAbstract {
    protected final ChineseCheckersBoard board;
    protected final boolean[][] visited = new boolean[25][17];
    protected final CornerMap corners;
    protected final List<IntPoint> offsets;

    public LogicUnitAbstract(Board board, CornerMap corners) {
        this.board = (ChineseCheckersBoard) board;
        this.corners = corners;
        this.offsets = new ArrayList<>();
        offsets.add(new IntPoint(1, 1));
        offsets.add(new IntPoint(-1, 1));
        offsets.add(new IntPoint(1, -1));
        offsets.add(new IntPoint(-1, -1));
        offsets.add(new IntPoint(-2, 0));
        offsets.add(new IntPoint(2, 0));
    }

    protected void setPossible(int x, int y) {
        if (board.isValidField(x, y)
                && board.isEmpty(x, y))
            board.setField(x, y, Field.Possible);
    }

    protected void setPossibleJump(int x_mid, int y_mid, int x, int y, Corner temp) {
        if (board.isValidField(x, y)
                && board.isEmpty(x, y)
                && board.hasPawn(x_mid, y_mid)
                && !visited[x][y]) {
            visited[x][y] = true;
            board.setField(x, y, Field.Possible);
            highlightJumpsRecursive(x, y, temp);
        }
    }

    protected void highlightJumpsRecursive(int x, int y, Corner temp) {
        if (temp == null) {
            for (IntPoint point : offsets) {
                setPossibleJump(x + point.getX(), y + point.getY(),
                        x + 2 * point.getX(), y + 2 * point.getY(), null);
            }
        } else {
            for (IntPoint point : offsets) {
                if (temp.has(x + 2 * point.getX(), y + 2 * point.getY())) {
                    setPossibleJump(x + point.getX(), y + point.getY(),
                            x + 2 * point.getX(), y + 2 * point.getY(), temp);
                }
            }
        }
    }

    public void highlightPossible(int x, int y, Field player) {
        if (board.getField(x, y) == player) {
            board.setField(x, y, Field.Chosen);
            Corner temp = corners.get(player);
            if (temp.has(x, y)) {
                for (IntPoint point : offsets) {
                    if (temp.has(x + point.getX(), y + point.getY())) {
                        setPossible(x + point.getX(), y + point.getY());
                    }
                }
                for (boolean[] row : visited) {
                    Arrays.fill(row, false);
                }
                highlightJumpsRecursive(x, y, temp);
            } else {
                for (IntPoint point : offsets) {
                    setPossible(x + point.getX(), y + point.getY());
                }
                for (boolean[] row : visited) {
                    Arrays.fill(row, false);
                }
                highlightJumpsRecursive(x, y, null);
            }
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
    }

    public abstract boolean isWinner(Field player);
}
