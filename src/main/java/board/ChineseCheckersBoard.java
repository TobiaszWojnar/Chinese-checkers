package board;

import board.corners.*;

import java.util.Arrays;

public class ChineseCheckersBoard extends Board {

    public ChineseCheckersBoard(int numberOfPlayers) {
        board = new Field[17][25];
        cleanBoard();
        prepareForPlayers(numberOfPlayers);
    }

    protected void prepareForPlayers(int numberOfPlayers) {
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

    protected void cleanBoard() {

        for (Field[] fields : board) {
            Arrays.fill(fields, Field.Empty);
        }

    }

    private void prepareForTwoPlayers() {
        setUpper();
        setLower(Field.Player2);
    }

    private void prepareForThreePlayers() {
        setUpper();
        setLowerRight(Field.Player2);
        setLowerLeft(Field.Player3);
    }

    private void prepareForFourPlayers() {
        setUpperLeft(Field.Player1);
        setUpperRight();
        setLowerRight(Field.Player3);
        setLowerLeft(Field.Player4);
    }

    private void prepareForSixPlayers() {
        setUpper();
        setUpperRight();
        setLowerRight(Field.Player3);
        setLower(Field.Player4);
        setLowerLeft(Field.Player5);
        setUpperLeft(Field.Player6);
    }

    private void setUpper() {
        for (IntPoint point : UpperCorner.getInstance().points) {
            setField(point.getX(), point.getY(), Field.Player1);
        }
    }

    private void setUpperRight() {
        for (IntPoint point : UpperRightCorner.getInstance().points) {
            setField(point.getX(), point.getY(), Field.Player2);
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