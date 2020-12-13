package board;

import board.corners.*;

import java.util.Arrays;

public class ChineseCheckersBoard extends Board{

    public ChineseCheckersBoard(int numberOfPlayers){
        board = new Field[17][25];
        cleanBoard();
        prepareForPlayers(numberOfPlayers);
    }

    public Board prepareForPlayers(int numberOfPlayers) {
        switch(numberOfPlayers){
            case 2:
                return prepareForTwoPlayers();
            case 3:
                return prepareForThreePlayers();
            case 4:
                return prepareForFourPlayers();
            case 6:
                return prepareForSixPlayers();
            default:
                return this;
                //TODO error handling
        }
    }
    protected void cleanBoard(){

        for (Field[] fields : board) {
            Arrays.fill(fields, Field.Empty);
        }
        /*
        for(Field[] row: board){
            for(Field field: row)
                field = Field.Empty;
        }
        */ //Does not set values
    }

    private Board prepareForTwoPlayers(){
        setUpper(Field.Player1);
        setLower(Field.Player2);
        return this;
    }

    private Board prepareForThreePlayers(){
        setUpper(Field.Player1);
        setLowerRight(Field.Player2);
        setLowerLeft(Field.Player3);
        return this;
    }

    private Board prepareForFourPlayers(){
        setUpperLeft(Field.Player1);
        setUpperRight(Field.Player2);
        setLowerRight(Field.Player3);
        setLowerLeft(Field.Player4);
        return this;
    }
    private Board prepareForSixPlayers(){
        setUpper(Field.Player1);
        setUpperRight(Field.Player2);
        setLowerRight(Field.Player3);
        setLower(Field.Player4);
        setLowerLeft(Field.Player5);
        setUpperLeft(Field.Player6);
        return this;
    }

    private void setUpper(Field field){
        for (IntPoint point : UpperCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setUpperRight(Field field){
        for (IntPoint point : UpperRightCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }
    private void setUpperLeft(Field field){
        for (IntPoint point : UpperLeftCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setLower(Field field){
        for (IntPoint point : LowerCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setLowerLeft(Field field){
        for (IntPoint point : LowerLeftCorner.getInstance().points) {
            setField(point.getX(), point.getY(), field);
        }
    }

    private void setLowerRight(Field field){
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