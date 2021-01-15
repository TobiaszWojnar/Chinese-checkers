package client;

import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
import client.chineseCheckers.GUI.ChineseCheckersGameGUI;

public class GameGUIFunctionalityTest {//TODO use mocking
    public static void main(String[] args) {
        ChineseBoardFactory factory = new ChineseBoardFactory();
        ChineseCheckersBoard board = factory.getBoard(args[1]);
        board.prepareForPlayers(Integer.parseInt(args[0]));
        new ChineseCheckersGameGUI(Integer.parseInt(args[0]),"Player1", board , "Player1");
    }
}
