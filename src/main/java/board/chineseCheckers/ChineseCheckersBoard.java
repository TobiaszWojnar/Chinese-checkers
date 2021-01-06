package board.chineseCheckers;

import board.Board;
import board.Field;
import board.IntPoint;
import board.chineseCheckers.corners.*;

import java.util.Arrays;

public abstract class ChineseCheckersBoard extends Board {

    protected int n;

    /*public ChineseCheckersBoard(int numberOfPlayers) {
        board = new Field[17][25];
        cleanBoard();
        prepareForPlayers(numberOfPlayers);
    }*/

    @Override
    public boolean isValidField(int x, int y) {
        if ((x + y) % 2 == 0) {
            for (int i = 0; i < n; i++) {
                if (y == i || y == 4 * n - i) {
                    return x >= 3 * n - i && x <= 3 * n + i;
                }
            }
            for (int i = n; i <= 2 * n; i++) {
                if (y == i || y == 4 * n - i) {
                    return x >= i - n && x <= 7 * n - i;
                }
            }
        }
        return false;
    }

    @Override
    public void prepareForPlayers(int numberOfPlayers) {
        LowerCorner.init(n);
        UpperCorner.init(n);
        LowerRightCorner.init(n);
        LowerLeftCorner.init(n);
        UpperRightCorner.init(n);
        UpperLeftCorner.init(n);
        switch (numberOfPlayers) {
            case 2:
                prepareForTwoPlayers();
                break;
            case 3:
                prepareForThreePlayers();
                break;
            case 4:
                prepareForFourPlayers();
                break;
            case 6:
                prepareForSixPlayers();
                break;
            default:
                break;
            //TODO error handling
        }
    }

    @Override
    protected void cleanBoard() {

        for (Field[] fields : board) {
            Arrays.fill(fields, Field.Empty);
        }

    }

    private void prepareForTwoPlayers() {
        setUpper(Field.Player1);
        setLower(Field.Player2);
    }

    private void prepareForThreePlayers() {
        setUpper(Field.Player1);
        setLowerRight(Field.Player2);
        setLowerLeft(Field.Player3);
    }

    private void prepareForFourPlayers() {
        setUpperLeft(Field.Player1);
        setUpperRight(Field.Player2);
        setLowerRight(Field.Player3);
        setLowerLeft(Field.Player4);
    }

    private void prepareForSixPlayers() {
        setUpper(Field.Player1);
        setUpperRight(Field.Player2);
        setLowerRight(Field.Player3);
        setLower(Field.Player4);
        setLowerLeft(Field.Player5);
        setUpperLeft(Field.Player6);
    }

    private void setUpper(Field field) {
        for (IntPoint point : UpperCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setUpperRight(Field field) {
        for (IntPoint point : UpperRightCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setUpperLeft(Field field) {
        for (IntPoint point : UpperLeftCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setLower(Field field) {
        for (IntPoint point : LowerCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setLowerLeft(Field field) {
        for (IntPoint point : LowerLeftCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setLowerRight(Field field) {
        for (IntPoint point : LowerRightCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    public boolean hasPawn(int x, int y) {
        return getField(x, y) != Field.Chosen && getField(x, y) != Field.Empty && getField(x, y) != Field.Possible;
    }

    public boolean isEmpty(int x, int y) {
        return getField(x, y) == Field.Empty;
    }
}