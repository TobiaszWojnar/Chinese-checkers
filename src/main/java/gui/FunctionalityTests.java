package gui;

import gui.ChineseCheckersGameGUI;
import gui.CommandLineBoardGUI;

public class FunctionalityTests {
    public static void main(String[] args) {
        CommandLineBoardGUI testGui;
        //for(int i:new int[] {6}){
        for(int i:new int[] {2,3,4,6}){
            testGui = new CommandLineBoardGUI(i);
            testGui.show();
            javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersGameGUI( i, "test room"+i ));
        }

        //javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersMenu().MenuWindow());

    }
}
