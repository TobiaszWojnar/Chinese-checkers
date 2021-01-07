package client;

import client.chineseCheckers.GUI.ChineseCheckersGameGUI;
import client.chineseCheckers.commandLineUI.CommandLineBoardUI;

public class FunctionalityTests {
    public static void main(String[] args) {
        CommandLineBoardUI testGui;
        //for(int i:new int[] {6}){
        for (int i : new int[]{2, 3, 4, 6}) {
            testGui = new CommandLineBoardUI(i, "normal");
            testGui.show();
            //javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersGameGUI( i, "test room"+i ));
        }

        //javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersMenu().MenuWindow());
    }
}
