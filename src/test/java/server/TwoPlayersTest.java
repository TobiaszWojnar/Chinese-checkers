package server;

import board.Board;
import board.Field;

import java.util.Scanner;

public class TwoPlayersTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Game game = new Game(2);
        game.start();

        scan.nextLine();
        show(game.getBoard());
    }

    private static char fieldToCharMapper(Field field){
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
                case Chosen:
                    return '@';
                default:
                    return '+';
            }
        }
        return '#';
    }

    public static void show(Board board){

        for(Field[] row: board.getBoard()){
            for(Field field: row) {
                System.out.print(fieldToCharMapper(field)+" ");
            }
            System.out.print('\n');
        }
    }

}
