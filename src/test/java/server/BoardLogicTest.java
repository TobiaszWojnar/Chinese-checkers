package server;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardLogicTest {

    @Test
    public void highlightTest() {
        // Tests if LogicUnit finds possible moves for a pawn correctly

        Board testBoard = new ChineseCheckersBoard(6);

        LogicUnit logic = new LogicUnit(testBoard);

        testBoard.setField(11,7, Field.Player1);
        testBoard.setField(9,7, Field.Player1);
        testBoard.setField(11,9, Field.Player3);
        testBoard.setField(13,7, Field.Player2);
        testBoard.setField(14,8, Field.Player5);
        testBoard.setField(14,6, Field.Player6);
        testBoard.setField(11,5, Field.Player1);
        testBoard.setField(11,11, Field.Player1);

        testBoard.setField(12,8, Field.Chosen);

        Board checkBoard = new ChineseCheckersBoard(6);

        // Clone testBoard
        checkBoard.setField(11,7, Field.Player1);
        checkBoard.setField(9,7, Field.Player1);
        checkBoard.setField(11,9, Field.Player3);
        checkBoard.setField(13,7, Field.Player2);
        checkBoard.setField(14,8, Field.Player5);
        checkBoard.setField(14,6, Field.Player6);
        checkBoard.setField(11,5, Field.Player1);
        checkBoard.setField(11,11, Field.Player1);

        checkBoard.setField(12,8, Field.Chosen);

        // Set expected values
        checkBoard.setField(13, 9, Field.Possible);
        checkBoard.setField(10, 8, Field.Possible);
        checkBoard.setField(16, 8, Field.Possible);
        checkBoard.setField(10, 6, Field.Possible);
        checkBoard.setField(12, 4, Field.Possible);
        checkBoard.setField(8, 8, Field.Possible);
        checkBoard.setField(10, 10, Field.Possible);
        checkBoard.setField(12, 12, Field.Possible);

        // Run function which should set testBoard to match checkBoard
        logic.highlightPossible(12, 8);

        // Check every field
        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y += 2)
                assertEquals(checkBoard.getField(x, y), testBoard.getField(x, y));

        logic.deselect(Field.Player1);

        Board checkBoard2 = new ChineseCheckersBoard(6);

        checkBoard2.setField(11,7, Field.Player1);
        checkBoard2.setField(9,7, Field.Player1);
        checkBoard2.setField(11,9, Field.Player3);
        checkBoard2.setField(13,7, Field.Player2);
        checkBoard2.setField(14,8, Field.Player5);
        checkBoard2.setField(14,6, Field.Player6);
        checkBoard2.setField(11,5, Field.Player1);
        checkBoard2.setField(11,11, Field.Player1);

        checkBoard2.setField(12,8, Field.Player1);

        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y += 2) {
                //System.out.println("x " + x + " " + y);
                assertEquals(checkBoard2.getField(x, y), testBoard.getField(x, y));
            }
    }

}
