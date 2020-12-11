package gui;

import board.Board;
import gui.board.ChineseCheckersBoardGUI;

import javax.swing.*;
import java.awt.*;

public class ChineseCheckersGameGUI extends JFrame {

    private final ChineseCheckersBoardGUI boardGui;

    //Is created by client
    public ChineseCheckersGameGUI(Board board, String roomId) {

        setTitle("ChineseCheckersGame room " + roomId);
        setSize(new Dimension(640,640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //TODO po utworzeniu możesz nie meć kompletu graczy
        boardGui = new ChineseCheckersBoardGUI(board);
        add(boardGui);
        boardGui.setListener(new ChineseCheckersBoardGUI.Listener() {
            @Override
            public void onClicked(int x, int y) {
                System.out.println("Server clicked x = " + x + "; y = " + y);
                //TODO
            }
        });

        //TODO menu for sending to requests 'Surrender', 'wait - no move'
        //TODO updating board based on what server sends
        //TODO popups for "You won", "Tie"

        //TODO menu for changing colors

    }

}
