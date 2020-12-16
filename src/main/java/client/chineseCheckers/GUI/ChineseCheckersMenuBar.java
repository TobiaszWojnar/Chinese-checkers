package client.chineseCheckers.GUI;

import board.Field;

import javax.swing.*;
import java.awt.*;

public class ChineseCheckersMenuBar extends JMenuBar {
    private String currentPlayer;
    private MenuBarListener listener;
    private final JMenuItem mCurrentPlayer;

    public ChineseCheckersMenuBar (ColorManager colorManager, int numberOfPlayers, String currentPlayer) {

        this.currentPlayer=currentPlayer;

        JMenu mColor = new JMenu("Change colors");
        mColor.setMaximumSize(new Dimension(100, mColor.getPreferredSize().height));
        add(mColor);

        JMenuItem mBackgroundColor = new JMenuItem("Background color");
        mBackgroundColor.setBackground(colorManager.getBackgroundColor());
        mBackgroundColor.addActionListener(e -> {
            Color temp=JColorChooser.showDialog(this, "Color Chooser", colorManager.getBackgroundColor());
            if(temp!=null) {
                colorManager.setBackgroundColor(temp);
                mBackgroundColor.setBackground(temp);
                listener.updateColors();
            }
        });
        mColor.add(mBackgroundColor);

        JMenuItem[] colorsMenu = new JMenuItem[numberOfPlayers+1];
        for(int i=0; i < numberOfPlayers+1;i++){
            colorsMenu[i] = new JMenuItem(Field.values()[i].name());
            colorsMenu[i].setBackground(colorManager.getMappedColor(i));
            int finalI = i;
            colorsMenu[i].addActionListener(e ->{
                Color temp=JColorChooser.showDialog(this, "Color Chooser", colorManager.getMappedColor(finalI));
                if(temp!=null) {
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
        mColorPossible.addActionListener(e ->{
            Color temp=JColorChooser.showDialog(this, "Color Chooser", colorManager.getMappedColor(j));
            if(temp!=null) {
                colorManager.putMap(Field.values()[j], temp);
                mColorPossible.setBackground(temp);
                listener.updateColors();
            }
        });
        mColor.add(mColorPossible);

        int i = Field.values().length - 1;
        JMenuItem mColorChosen = new JMenuItem(Field.values()[i].name());
        mColorChosen.setBackground(colorManager.getMappedColor(i));
        mColorChosen.addActionListener(e ->{
            Color temp=JColorChooser.showDialog(this, "Color Chooser", colorManager.getMappedColor(i));
            if(temp!=null) {
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

        mCurrentPlayer = new JMenuItem("Turn: "+currentPlayer);
        //mCurrentPlayer.setMaximumSize(new Dimension(100, mSkip.getPreferredSize().height));
        add(mCurrentPlayer);

    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        mCurrentPlayer.setText("Turn: "+currentPlayer);
    }

    public void setListener(MenuBarListener listener){
        this.listener = listener;
    }

    public interface MenuBarListener{
        void resign();
        void skip();
        void updateColors();
    }
}
