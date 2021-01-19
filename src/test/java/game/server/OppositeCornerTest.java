package game.server;

import game.board.IntPoint;
import game.board.chineseCheckers.ChineseBoardFactory;
import game.board.chineseCheckers.ChineseCheckersBoard;
import game.board.Field;
import game.board.chineseCheckers.corners.UpperCorner;
import org.junit.Test;
import game.server.chineseCheckers.datastructures.CornerMap;
import game.server.chineseCheckers.logic.LogicUnitAllFilled;

import static org.junit.Assert.assertEquals;

public class OppositeCornerTest {

    @Test
    public void cornerTest() {

        ChineseBoardFactory factory = new ChineseBoardFactory();


        ChineseCheckersBoard checkBoard = factory.getBoard("normal");
        checkBoard.prepareForPlayers(2);
        ChineseCheckersBoard testBoard = factory.getBoard("normal");
        testBoard.prepareForPlayers(2);
        CornerMap corners = new CornerMap(2);

        LogicUnitAllFilled logic = new LogicUnitAllFilled(testBoard, corners);


        testBoard.setField(13, 13, Field.Empty);
        testBoard.setField(12, 16, Field.Empty);
        testBoard.setField(14, 14, Field.Player1);


        checkBoard.setField(13, 13, Field.Possible);
        checkBoard.setField(12, 16, Field.Possible);
        checkBoard.setField(14, 14, Field.Chosen);

        logic.highlightPossible(14, 14, Field.Player1);


        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y++) {
                //System.out.println(x + " " + y);
                assertEquals(checkBoard.getField(x, y), testBoard.getField(x, y));
            }
    }
}
