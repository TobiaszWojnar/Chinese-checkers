package server;

import board.IntPoint;
import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
import board.Field;
import board.chineseCheckers.corners.UpperCorner;
import org.junit.Test;
import server.chineseCheckers.datastructures.CornerMap;
import server.chineseCheckers.logic.LogicUnitAllFilled;

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

        //for (corners.get(Field.Player2).)

        /*testBoard.show();
        checkBoard.show();*/

        testBoard.setField(13, 13, Field.Empty);
        testBoard.setField(12, 16, Field.Empty);
        testBoard.setField(14, 14, Field.Player1);


        checkBoard.setField(13, 13, Field.Possible);
        checkBoard.setField(12, 16, Field.Possible);
        checkBoard.setField(14, 14, Field.Chosen);

        //checkBoard.show();

        logic.highlightPossible(14, 14, Field.Player1);


        for (IntPoint point : UpperCorner.getInstance().points) {
            System.out.println("x " + point.getX() + " " + point.getY());
        }

        boolean temp = corners.get(Field.Player2).has(12, 2);
        System.out.println(temp);
        System.out.println(corners.size());

        for (int x = 0; x < 25; x++)
            for (int y = 0; y < 17; y++) {
                System.out.println(x + " " + y);
                assertEquals(checkBoard.getField(x, y), testBoard.getField(x, y));
            }
    }
}
