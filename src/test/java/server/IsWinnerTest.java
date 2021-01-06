package server;

import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
import board.Field;
import org.junit.Test;
import server.chineseCheckers.datastructures.CornerMap;
import server.chineseCheckers.logic.LogicUnitAllFilled;

import static org.junit.Assert.*;


public class IsWinnerTest {

    @Test
    public void isWinnerTest() {
        ChineseBoardFactory factory = new ChineseBoardFactory();

        ChineseCheckersBoard testBoard = factory.getBoard("normal");
        testBoard.prepareForPlayers(2);
        LogicUnitAllFilled logic = new LogicUnitAllFilled(testBoard, new CornerMap(2));

        assertFalse(logic.isWinner(Field.Player2));

        testBoard.setField(12,0,Field.Player2);
        testBoard.setField(11,1,Field.Player2);
        testBoard.setField(13,1,Field.Player2);
        testBoard.setField(10,2,Field.Player2);
        testBoard.setField(12,2,Field.Player2);
        testBoard.setField(14,2,Field.Player2);
        testBoard.setField(9,3,Field.Player2);
        testBoard.setField(11,3,Field.Player2);
        testBoard.setField(13,3,Field.Player2);
        testBoard.setField(15,3,Field.Player2);

        assertTrue(logic.isWinner(Field.Player2));
    }
}
