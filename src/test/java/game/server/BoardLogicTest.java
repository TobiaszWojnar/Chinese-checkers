package game.server;

import game.board.Board;
import game.board.chineseCheckers.ChineseBoardFactory;
import game.board.chineseCheckers.ChineseCheckersBoard;
import game.board.Field;
import org.junit.Test;
import game.server.chineseCheckers.datastructures.CornerMap;
import game.server.chineseCheckers.logic.LogicUnitAllFilled;

import static org.junit.Assert.assertEquals;

public class BoardLogicTest {

    @Test
    public void highlightTest() {
        // Tests if LogicUnit performs every step of moving a pawn correctly

        ChineseBoardFactory factory = new ChineseBoardFactory();

        ChineseCheckersBoard testBoard = factory.getBoard("normal");
        testBoard.prepareForPlayers(6);

        LogicUnitAllFilled logic = new LogicUnitAllFilled(testBoard, new CornerMap(6));

        testBoard.setField(11, 7, Field.Player1);
        testBoard.setField(9, 7, Field.Player1);
        testBoard.setField(11, 9, Field.Player3);
        testBoard.setField(13, 7, Field.Player2);
        testBoard.setField(14, 8, Field.Player5);
        testBoard.setField(14, 6, Field.Player6);
        testBoard.setField(11, 5, Field.Player1);
        testBoard.setField(11, 11, Field.Player1);

        testBoard.setField(12, 8, Field.Player1);

        ChineseCheckersBoard checkBoard = factory.getBoard("normal");
        checkBoard.prepareForPlayers(6);

        // Clone testBoard
        checkBoard.setField(11, 7, Field.Player1);
        checkBoard.setField(9, 7, Field.Player1);
        checkBoard.setField(11, 9, Field.Player3);
        checkBoard.setField(13, 7, Field.Player2);
        checkBoard.setField(14, 8, Field.Player5);
        checkBoard.setField(14, 6, Field.Player6);
        checkBoard.setField(11, 5, Field.Player1);
        checkBoard.setField(11, 11, Field.Player1);

        checkBoard.setField(12, 8, Field.Chosen);

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
        logic.highlightPossible(12, 8, Field.Player1);

        // Check every field
        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y++) {
                //System.out.println("x " + x + " " + y);
                assertEquals(checkBoard.getField(x, y), testBoard.getField(x, y));
            }

        logic.deselect(12, 8, Field.Player1);

        ChineseCheckersBoard checkBoard2 = factory.getBoard("normal");
        checkBoard2.prepareForPlayers(6);

        checkBoard2.setField(11, 7, Field.Player1);
        checkBoard2.setField(9, 7, Field.Player1);
        checkBoard2.setField(11, 9, Field.Player3);
        checkBoard2.setField(13, 7, Field.Player2);
        checkBoard2.setField(14, 8, Field.Player5);
        checkBoard2.setField(14, 6, Field.Player6);
        checkBoard2.setField(11, 5, Field.Player1);
        checkBoard2.setField(11, 11, Field.Player1);

        checkBoard2.setField(12, 8, Field.Player1);

        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y++) {
                //System.out.println("x " + x + " " + y);
                assertEquals(checkBoard2.getField(x, y), testBoard.getField(x, y));
            }

        checkBoard2.setField(12, 8, Field.Empty);
        checkBoard2.setField(8, 8, Field.Player1);

        logic.highlightPossible(12, 8, Field.Player1);
        logic.move(8, 8, 12, 8, Field.Player1);

        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y++) {
                //System.out.println("x " + x + " " + y);
                assertEquals(checkBoard2.getField(x, y), testBoard.getField(x, y));
            }

    }

}
