package client.chineseCheckers.GUI;

import board.Board;
import board.ChineseCheckersBoard;

import javax.swing.*;
import java.awt.*;

public class ChineseCheckersGameGUI extends JFrame {

    private final ChineseCheckersBoardGUI boardGui;
    private GameGuiListener gameGuiListener;

    //Is created by client
    public ChineseCheckersGameGUI(int numberOfPlayers, String roomId, Board board, ColorManager colorManager) {

        System.out.println("Creating board gui");
        boardGui = new ChineseCheckersBoardGUI(board, colorManager);
        add(boardGui);
        boardGui.setListener((x, y) -> {
            System.out.println("Server clicked x = " + x + "; y = " + y);
            gameGuiListener.onClicked(x, y);
        });

        System.out.println("Creating menubar");
        ChineseCheckersMenuBar menuBar = new ChineseCheckersMenuBar(colorManager, numberOfPlayers);
        setJMenuBar(menuBar);
        menuBar.setListener(new ChineseCheckersMenuBar.MenuBarListener() {
            @Override
            public void resign() {
                //TODO catch request and do sth
                //gameGuiListener.onResigned();
                System.out.println("To server I resign");
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

        System.out.println("Creating window");
        setTitle("ChineseCheckersGame room: " + roomId);
        setSize(new Dimension(650,650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * updates local board and repaints
     * @param board updated board
     */
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

/*
    public interface GameGuiListener {
        void onClicked(int x, int y);//Waits for board or message or both
        void onSkipped();            //Waits for board or message or both
        void onResigned();           //Waits for message
        //Liczba graczy co jakiś czas
    }
*/
}
