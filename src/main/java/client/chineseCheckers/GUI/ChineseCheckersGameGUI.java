package client.chineseCheckers.GUI;

import board.chineseCheckers.ChineseCheckersBoard;

import javax.swing.*;
import java.awt.*;

public class ChineseCheckersGameGUI extends JFrame {

    private final ChineseCheckersBoardGUI boardGui;
    private GameGuiListener gameGuiListener;
    private final ChineseCheckersMenuBar menuBar;
    private String currentPlayer;

    public ChineseCheckersGameGUI(
            int numberOfPlayers, String playerNumber, ChineseCheckersBoard board, String currentPlayer) {//TODO roomId?
            //TODO show list of players with colors, change your number to 'You'
        this.currentPlayer=currentPlayer;

        ColorManager colorManager = new ColorManager(numberOfPlayers);

        System.out.println("Creating board gui");

        boardGui = new ChineseCheckersBoardGUI(board, colorManager);
        add(boardGui);
        boardGui.setListener((x, y) -> {
            System.out.println("Server clicked x = " + x + "; y = " + y);
            gameGuiListener.onClicked(x, y);
        });

        System.out.println("Creating menuBar");
        menuBar = new ChineseCheckersMenuBar(colorManager, numberOfPlayers, currentPlayer);
        setJMenuBar(menuBar);
        menuBar.setListener(new ChineseCheckersMenuBar.MenuBarListener() {
            @Override
            public void resign() {
                gameGuiListener.onResigned();
                System.out.println("To server I resign");
            }

            @Override
            public void skip() {
                gameGuiListener.onSkipped();
                System.out.println("To server I skip");
            }

            @Override
            public void updateColors() {
                boardGui.repaint();
            }
        });

        System.out.println("Creating window");
        setTitle("Chinese Checkers Game. You are " + playerNumber);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800,800));
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

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        menuBar.setCurrentPlayer(currentPlayer);
    }
}
