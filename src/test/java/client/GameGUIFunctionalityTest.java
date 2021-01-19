package client;

import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
import client.chineseCheckers.GUI.ChineseCheckersGameGUI;

import javax.swing.*;

public class GameGUIFunctionalityTest {//TODO use mocking
    public static void main(String[] args) {
        ChineseBoardFactory factory = new ChineseBoardFactory();
        ChineseCheckersBoard board = factory.getBoard(args[1]);
        board.prepareForPlayers(Integer.parseInt(args[0]));
        ChineseCheckersGameGUI d =new ChineseCheckersGameGUI(Integer.parseInt(args[0]),"Player1", board , "Player1");
        //JOptionPane.showMessageDialog(d, "Player1+ is the winner", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
