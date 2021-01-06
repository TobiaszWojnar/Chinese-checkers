package server;

import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
import board.Field;
import org.junit.Test;
import server.chineseCheckers.datastructures.CornerMap;
import server.chineseCheckers.logic.LogicUnitAllFilled;

import static org.junit.Assert.*;

public class ValidFieldTest {

    @Test
    public void validFieldTest() {
        ChineseBoardFactory factory = new ChineseBoardFactory();

        ChineseCheckersBoard testBoard = factory.getBoard("small");
        testBoard.prepareForPlayers(6);
        assertFalse(testBoard.isValidField(18,4));
        assertTrue(testBoard.isValidField(18,3));

        testBoard = factory.getBoard("big");
        testBoard.prepareForPlayers(6);
        assertFalse(testBoard.isValidField(24,12));
        assertTrue(testBoard.isValidField(23,12));
    }
}
