package client;

import board.ChineseCheckersBoard;
import gui.ChineseCheckersGameGUI;
import gui.menu.ChineseCheckersMenu;
import gui.CommandLineBoardGUI;

public class Main {
    public static void main(String[] args) {
        CommandLineBoardGUI testGui;
        for(int i:new int[] {6}){
        //        for(int i:new int[] {2,3,4,6}){
            testGui = new CommandLineBoardGUI(i);
            testGui.show();
            javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersGameGUI(new ChineseCheckersBoard(i), "test room"+i));
        }

        //javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersMenu().MenuWindow());

    }
}
