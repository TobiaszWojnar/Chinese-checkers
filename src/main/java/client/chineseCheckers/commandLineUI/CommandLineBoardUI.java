package client.chineseCheckers.commandLineUI;

import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
import board.Field;

/**
 * First Mocking GUI. Enabled to define interfaces and debug board.
 */
public class CommandLineBoardUI {
    private final ChineseCheckersBoard board;

    /**
     * Constructor creates board. Uses Factory design pattern
     *
     * @param numberOfPlayers should be 2,3,4 or 6
     * @param boardType defines size
     */
    public CommandLineBoardUI(int numberOfPlayers, String boardType) {
        ChineseBoardFactory factory = new ChineseBoardFactory();
        board = factory.getBoard(boardType);
        board.prepareForPlayers(numberOfPlayers);
    }

    /**
     * Displays on standard output board.
     */
    public void show() {

        for (Field[] row : board.getBoard()) {
            for (Field field : row) {
                System.out.print(fieldToCharMapper(field) + " ");
            }
            System.out.print('\n');
        }
    }

    /**
     * Maps field types to letters for #show() function
     *
     * @param field Specific field to be mapped
     * @return character to be printed
     */
    private char fieldToCharMapper(Field field) {
        if (field != null) {
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
