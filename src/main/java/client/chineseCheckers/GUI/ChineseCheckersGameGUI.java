package client.chineseCheckers.GUI;

import board.chineseCheckers.ChineseCheckersBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * GUI of game connects all its components and communicate with client.
 */
public class ChineseCheckersGameGUI extends JFrame  {

    private final ChineseCheckersBoardGUI boardGui;
    private GameGuiListener gameGuiListener;
    private final ChineseCheckersMenuBar menuBar;

    /**
     * Constructor gets all information about the game, starts all gui parts and listeners.
     *
     * @param numberOfPlayers integer equals to 2,3,4 or 6
     * @param playerNumber    String like "PlayerX"
     * @param board           Board on which we play
     * @param currentPlayer   String like "PlayerX"
     */
    public ChineseCheckersGameGUI(
            int numberOfPlayers, String playerNumber, ChineseCheckersBoard board, String currentPlayer) {//TODO game/room id?

        ColorManager colorManager = new ColorManager(numberOfPlayers);

        System.out.println("Creating board gui");

        int player = Integer.parseInt(playerNumber.substring(playerNumber.length() - 1));
        boardGui = new ChineseCheckersBoardGUI(board, colorManager, initialAngle(player, numberOfPlayers));

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

            @Override
            public void rotate() {
                boardGui.rotate();
            }
        });

        System.out.println("Creating window");
        setTitle("Chinese Checkers Game. You are " + playerNumber);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameGuiListener.onClose();
            }
        });

        setMinimumSize(new Dimension(640, 640));
        setPreferredSize(new Dimension(800, 800));
        pack();

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                boardGui.update(getContentPane().getSize());
                System.out.println(getContentPane().getSize().height+" "+getContentPane().getSize().width);
            }
        });

        setResizable(true);
        setVisible(true);
    }

    /**
     * updates local board and repaints
     *
     * @param board updated board
     */
    public void updateBoard(ChineseCheckersBoard board) {
        boardGui.update(board);
    }

    /**
     * Calculate initial rotation offset.
     * @param player player number
     * @param numOfPlayers number of players in the game.
     * @return ange in degree
     */
    private int initialAngle(int player, int numOfPlayers) {
        switch (numOfPlayers) {
            case 2:
                return 180 * (player % 2);
            case 3:
                switch (player) {
                    case 1:
                        return 180;
                    case 2:
                        return 60;
                    case 3:
                        return 300;
                }
            case 4:
                switch (player) {
                    case 1:
                        return 240;
                    case 2:
                        return 120;
                    case 3:
                        return 60;
                    case 4:
                        return 300;
                }
            case 6:
                return (240 - player * 60) % 360;
            default:
                return 0;
        }
    }

    /**
     * Possible messages 'You lose', 'You win', 'It is a tie'
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Setter of Listener enabling client to get updates.
     * @param gameGuiListener Listener enabling client to get updates
     */
    public void setListener(GameGuiListener gameGuiListener) {
        this.gameGuiListener = gameGuiListener;
    }

    /**
     * Setter used to display whose turn is it.
     *
     * @param currentPlayer name of player.
     */
    public void setCurrentPlayer(String currentPlayer) {
        menuBar.setCurrentPlayer(currentPlayer);
    }
}
