package client;

import gui.menu.ChineseCheckersMenu;
import gui.CommandLineBoardGUI;

public class Main {
    public static void main(String[] args) {
        CommandLineBoardGUI testGui;
        testGui = new CommandLineBoardGUI(2);
        testGui.show();
        testGui = new CommandLineBoardGUI(3);
        testGui.show();
        testGui = new CommandLineBoardGUI(4);
        testGui.show();
        testGui = new CommandLineBoardGUI(6);
        testGui.show();

        javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersMenu().MenuWindow());
    }
}
