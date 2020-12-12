package client.chineseCheckers.commandLineUI;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;

public class CommandLineBoardUI {
    private final Board board;//TODO names is board a matrix of Field objects or is it a Board object

    public CommandLineBoardUI(int numberOfPlayers){
        board = new ChineseCheckersBoard(numberOfPlayers);
    }

    public void show(){

        for(Field[] row: board.getBoard()){
            for(Field field: row) {
                System.out.print(fieldToCharMapper(field)+" ");
            }
            System.out.print('\n');
        }
    }

    private char fieldToCharMapper(Field field){
        if(field!=null) {
            switch (field) {
                case Player1:
                    return 'A';
                case Player2:
                    return 'B';
                case Player3:
                    return 'C';
                case Player4:
                    return 'D';
                case Player5:
                    return 'E';
                case Player6:
                    return 'F';
                case Empty:
                    return ' ';
                case Possible:
                    return '*';
                default:
                    return '+';
            }
        }
        return '#';
    }
}
