package com.javamaster.model.board.chineseCheckers;

import com.javamaster.model.board.IntPoint;
import com.javamaster.model.board.chineseCheckers.corners.*;
import com.javamaster.model.Field;
import com.javamaster.model.board.Board;

import java.util.Arrays;

/**
 * Class providing a board for Chinese Checkers game
 */
public abstract class ChineseCheckersBoard extends Board {

    protected int n;

    /**
     * Checks if given field is valid
     *
     * @param x x cooridnate of the field
     * @param y y cooridnate of the field
     * @return true of field is valid false otherwise
     */
    @Override
    public boolean isValidField(int x, int y) {
        if ((x + y) % 2 == n % 2) {
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

    /**
     * Prepares board for the beggining of the game by setting field to different players
     * depending on the number of players and initializes singletons of corners
     *
     * @param numberOfPlayers number of players
     * @see com.javamaster.model.board.chineseCheckers.corners.Corner
     */
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

    /**
     * Checks if there is a pawn on a given field
     *
     * @param x x coordinate of the field
     * @param y y coordinate of the field
     * @return true if there is a pawn on the field false otherwise
     */
    public boolean hasPawn(int x, int y) {
        return getField(x, y) != Field.Chosen && getField(x, y) != Field.Empty && getField(x, y) != Field.Possible;
    }

    /**
     * Checks if a given field is empty
     *
     * @param x x coordinate of the field
     * @param y y coordinate of the field
     * @return true if the field is empty false otherwise
     */
    public boolean isEmpty(int x, int y) {
        return getField(x, y) == Field.Empty;
    }

    /**
     * Getter for the size of the board
     *
     * @return size of the board
     */
    public int getN() {
        return n;
    }
}

