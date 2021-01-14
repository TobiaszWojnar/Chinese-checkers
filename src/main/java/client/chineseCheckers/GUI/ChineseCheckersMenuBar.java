package client.chineseCheckers.GUI;

import board.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Menu bar enabling to skip, resign, change color and displaying current Player
 */
public class ChineseCheckersMenuBar extends JMenuBar {
    //private String currentPlayer;
    private MenuBarListener listener;
    private final JMenuItem mCurrentPlayer;
    private final ColorManager colorManager;

    /**
     * Constructor created buttons and sets actions when clicked.
     *
     * @param colorManager contains colors of all players and board.
     * @param numberOfPlayers numberOfPlayers in game
     * @param currentPlayer currentPlayer
     */
    public ChineseCheckersMenuBar(ColorManager colorManager, int numberOfPlayers, String currentPlayer) {
        this.colorManager=colorManager;
        //this.currentPlayer = currentPlayer;

        JMenu mColor = new JMenu("Change colors");
        mColor.setMaximumSize(new Dimension(100, mColor.getPreferredSize().height));
        add(mColor);

        JMenuItem mBackgroundColor = new JMenuItem("Background color");
        mBackgroundColor.setBackground(colorManager.getBackgroundColor());
        mBackgroundColor.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(this, "Color Chooser", colorManager.getBackgroundColor());
            if (temp != null) {
                colorManager.setBackgroundColor(temp);
                mBackgroundColor.setBackground(temp);
                listener.updateColors();
            }
        });
        mColor.add(mBackgroundColor);

        JMenuItem[] colorsMenu = new JMenuItem[numberOfPlayers + 1];
        for (int i = 0; i < numberOfPlayers + 1; i++) {
            colorsMenu[i] = new JMenuItem(Field.values()[i].name());
            colorsMenu[i].setBackground(colorManager.getMappedColor(i));
            int finalI = i;
            colorsMenu[i].addActionListener(e -> {
                Color temp = JColorChooser.showDialog(this, "Color Chooser", colorManager.getMappedColor(finalI));
                if (temp != null) {
                    colorManager.putMap(Field.values()[finalI], temp);
                    colorsMenu[finalI].setBackground(temp);
                    listener.updateColors();
                }
            });
            mColor.add(colorsMenu[i]);
        }
        int j = Field.values().length - 2;
        JMenuItem mColorPossible = new JMenuItem(Field.values()[j].name());
        mColorPossible.setBackground(colorManager.getMappedColor(j));
        mColorPossible.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(this, "Color Chooser", colorManager.getMappedColor(j));
            if (temp != null) {
                colorManager.putMap(Field.values()[j], temp);
                mColorPossible.setBackground(temp);
                listener.updateColors();
            }
        });
        mColor.add(mColorPossible);

        int i = Field.values().length - 1;
        JMenuItem mColorChosen = new JMenuItem(Field.values()[i].name());
        mColorChosen.setBackground(colorManager.getMappedColor(i));
        mColorChosen.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(this, "Color Chooser", colorManager.getMappedColor(i));
            if (temp != null) {
                colorManager.putMap(Field.values()[i], temp);
                mColorChosen.setBackground(temp);
                listener.updateColors();
            }
        });
        mColor.add(mColorChosen);

        JMenuItem mResign = new JMenuItem("Resign");
        mResign.setMaximumSize(new Dimension(100, mResign.getPreferredSize().height));
        add(mResign);
        mResign.addActionListener(e -> listener.resign());

        JMenuItem mSkip = new JMenuItem("Skip");
        mSkip.setMaximumSize(new Dimension(100, mSkip.getPreferredSize().height));
        add(mSkip);
        mSkip.addActionListener(e -> listener.skip());

        JMenuItem mRotate = new JMenuItem("Rotate");
        mRotate.setMaximumSize(new Dimension(100, mRotate.getPreferredSize().height));
        add(mRotate);
        mRotate.addActionListener(e -> listener.rotate());

        mCurrentPlayer = new JMenuItem();
        setCurrentPlayer(currentPlayer);
        add(mCurrentPlayer);

    }

    /**
     * changes displaying text in menu bar
     * @param currentPlayer currentPlayer
     */
    public void setCurrentPlayer(String currentPlayer) {
        //this.currentPlayer = currentPlayer;
        mCurrentPlayer.setForeground(colorManager.getMappedColor(currentPlayer));
        mCurrentPlayer.setText("Turn: " + currentPlayer);
    }
    /**
     * Setter of Listener enabling client to get updates.
     * @param listener Listener enabling client to get updates
     */
    public void setListener(MenuBarListener listener) {
        this.listener = listener;
    }

    /**
     * 3 actions need to pe passed to board or server
     */
    public interface MenuBarListener {
        void resign();
        void skip();
        void updateColors();
        void rotate();
    }
}
