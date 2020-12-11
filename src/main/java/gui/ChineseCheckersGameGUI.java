package gui;

import board.Board;
import board.ChineseCheckersBoard;
import gui.board.ChineseCheckersBoardGUI;

import javax.swing.*;
import java.awt.*;

public class ChineseCheckersGameGUI extends JFrame {

    private final ChineseCheckersBoardGUI boardGui;
    private GameGuiListener gameGuiListener;

    //Is created by client
    public ChineseCheckersGameGUI(Board board, String roomId) {

        setTitle("ChineseCheckersGame room= " + roomId);
        setSize(new Dimension(640,640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //TODO po utworzeniu możesz nie mieć kompletu graczy
        boardGui = new ChineseCheckersBoardGUI(board);
        add(boardGui);
        boardGui.setListener(new ChineseCheckersBoardGUI.BoardGuiListener() {
            @Override
            public void onClicked(int x, int y) {
                System.out.println("Server clicked x = " + x + "; y = " + y);
                //TODO catch request and do sth
                //gameGuiListener.onClicked(x,y);
                showMessage("Server clicked x = " + x + "; y = " + y);
            }
        });

        //TODO menu for sending to requests 'Surrender', 'wait - no move'

        //TODO menu for changing colors

    }
    public void updateBoard(ChineseCheckersBoard board){
        boardGui.boardUpdate(board);
    }
    /**
     * Possible messages 'You lose', 'You win', 'It is a tie'
     */
    public void showMessage(String message){//TODO enum for types of messages
        JOptionPane.showMessageDialog(this, message,"Info",JOptionPane.INFORMATION_MESSAGE);
    }

    public void setListener(GameGuiListener gameGuiListener){
        this.gameGuiListener = gameGuiListener;
    }

    public interface GameGuiListener {
        void onClicked(int x, int y);
    }
}
