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
    public ChineseCheckersGameGUI(int numberOfPlayers, String roomId) {

        setTitle("ChineseCheckersGame room= " + roomId);
        setSize(new Dimension(650,650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        Board board = new ChineseCheckersBoard(numberOfPlayers);
        ColorManager colorManager = new ColorManager(numberOfPlayers);

        //TODO po utworzeniu możesz nie mieć kompletu graczy
        boardGui = new ChineseCheckersBoardGUI(board, colorManager);
        add(boardGui);
        boardGui.setListener((x, y) -> {
            System.out.println("Server clicked x = " + x + "; y = " + y);
            //TODO catch request and do sth
            //gameGuiListener.onClicked(x,y);
        });

        ChineseCheckersMenuBar menuBar = new ChineseCheckersMenuBar(colorManager, numberOfPlayers);
        setJMenuBar(menuBar);
        menuBar.setListener(new ChineseCheckersMenuBar.MenuBarListener() {
            @Override
            public void resign() {
                //TODO catch request and do sth
                //gameGuiListener.onResigned();
                System.out.println("TO server I resign");
            }

            @Override
            public void skip() {
                //TODO catch request and do sth
                //gameGuiListener.onSkipped();
                System.out.println("To server I skip");
            }

            @Override
            public void updateColors() {
                boardGui.repaint();
            }
        });

    }
    public void updateBoard(ChineseCheckersBoard board){
        boardGui.boardUpdate(board);
    }
    /**
     * Possible messages 'You lose', 'You win', 'It is a tie'
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message,"Info",JOptionPane.INFORMATION_MESSAGE);
    }

    public void setListener(GameGuiListener gameGuiListener){
        this.gameGuiListener = gameGuiListener;
    }

    public interface GameGuiListener {
        void onClicked(int x, int y);
        void onSkipped();
        void onResigned();
    }
}
