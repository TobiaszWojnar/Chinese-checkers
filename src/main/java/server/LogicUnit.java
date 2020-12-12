package server;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;

import java.util.Arrays;

public class LogicUnit {
    private final ChineseCheckersBoard board;
    private final boolean[][] visited = new boolean[17][24];

    public LogicUnit(Board board) {
        this.board = (ChineseCheckersBoard)board;
    }

    void setPossible(int x, int y) {
        if (board.isValidField(x, y) && board.isEmpty(x, y))
            board.setField(x, y, Field.Possible);
    }

    void setPossibleJump(int x_mid, int y_mid, int x, int y) {
        if (board.isValidField(x, y) && board.isEmpty(x, y) && board.hasPawn(x_mid, y_mid) && !visited[x][y]) {
            visited[x][y] = true;
            setPossible(x, y);
            highlightJumpsRecursive(x, y);
        }
    }

    void highlightJumpsRecursive(int x, int y) {
        setPossibleJump(x - 1, y + 1, x - 2, y + 2);
        setPossibleJump(x + 1, y + 1, x + 2, y + 2);
        setPossibleJump(x - 1, y - 1, x - 2, y - 2);
        setPossibleJump(x + 1, y - 1, x + 2, y - 2);
        setPossibleJump(x - 2, y, x - 4, y);
        setPossibleJump(x + 2, y, x + 4, y);
    }

    void highlightPossible(int x, int y) {
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

    void deselect(Field field) {
        board.deselect(field);
    }
}
