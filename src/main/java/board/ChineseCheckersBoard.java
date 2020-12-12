package board;

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
        setField(12,0,field);

        setField(11,1,field);
        setField(13,1,field);

        setField(10,2,field);
        setField(12,2,field);
        setField(14,2,field);

        setField(9,3,field);
        setField(11,3,field);
        setField(13,3,field);
        setField(15,3,field);
    }

    private void setUpperRight(Field field){
        setField(18, 4, field);
        setField(20, 4, field);
        setField(22, 4, field);
        setField(24, 4, field);

        setField(19, 5, field);
        setField(21, 5, field);
        setField(23, 5, field);

        setField(20, 6, field);
        setField(22, 6, field);

        setField(21, 7, field);

    }
    private void setUpperLeft(Field field){
        setField(0,4, field);
        setField(2,4, field);
        setField(4,4, field);
        setField(6,4, field);

        setField(1,5, field);
        setField(3,5, field);
        setField(5,5, field);

        setField(2,6, field);
        setField(4,6, field);

        setField(3,7, field);

    }

    private void setLower(Field field){

        setField(9,13,field);
        setField(11,13,field);
        setField(13,13,field);
        setField(15,13,field);

        setField(10,14,field);
        setField(12,14,field);
        setField(14,14,field);

        setField(11,15,field);
        setField(13,15,field);

        setField(12,16,field);
    }

    private void setLowerLeft(Field field){
        setField(3,9,field);

        setField(2,10,field);
        setField(4,10,field);

        setField(1,11,field);
        setField(3,11,field);
        setField(5,11,field);

        setField(0,12,field);
        setField(2,12,field);
        setField(4,12,field);
        setField(6,12,field);

    }

    private void setLowerRight(Field field){

        setField(21,9,field);

        setField(20,10,field);
        setField(22,10,field);

        setField(19,11,field);
        setField(21,11,field);
        setField(23,11,field);

        setField(18,12,field);
        setField(20,12,field);
        setField(22,12,field);
        setField(24,12,field);

    }
    public boolean hasPawn(int x, int y) {
        return getField(x, y) != Field.Chosen && getField(x, y) != Field.Empty && getField(x, y) != Field.Possible;
    }

    public boolean isEmpty(int x, int y) {
        return getField(x, y) == Field.Empty;
    }

    public void deselect(Field player) {
    for (int i = 0; i < getWidth(); i++)
        for (int j = 0; j < getHeight(); j++) {
            if (getField(i, j) == Field.Possible)
                setField(i, j, Field.Empty);
            if (getField(i, j) == Field.Chosen) {
                setField(i, j, player);
            }
        }

    }

}