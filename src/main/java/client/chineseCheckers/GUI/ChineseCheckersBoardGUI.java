package client.chineseCheckers.GUI;

import board.Board;
import board.chineseCheckers.ChineseCheckersBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChineseCheckersBoardGUI extends JPanel implements MouseListener {
    private ChineseCheckersBoard board;
    private final int pawnSize = 35;
    private final int xOffset = 55;
    private final ColorManager colorManager;
    private BoardGuiListener boardGuiListener;

    public ChineseCheckersBoardGUI (ChineseCheckersBoard board, ColorManager colorManager){
        this.board=board;
        this.colorManager=colorManager;

        addMouseListener(this);
    }

    public void paint(Graphics g){
        g.setColor(colorManager.getBackgroundColor());
        System.out.println(g.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        for(int y = 0; y<board.getHeight();y++){
            for(int x = 0; x<board.getWidth();x++) {
                if(board.isValidField(x,y))
                    paintElement(g,x,y);
            }
        }
    }



    private void paintElement (Graphics g, int x, int y){
        g.setColor(colorManager.getMappedColor(board.getField(x, y)));

        g.fillOval((int)(xOffset + x*pawnSize*0.6),y* pawnSize, pawnSize, pawnSize);
    }

    public void boardUpdate (ChineseCheckersBoard board){
        this.board=board;
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent mE) {
        int x;
        int y = mE.getY()/pawnSize;
        if(y%2==0)
            x = 2*(int)((mE.getX()-xOffset)/(pawnSize*1.2));
        else
            x = 2*(int)((mE.getX()-xOffset-pawnSize*0.6)/(pawnSize*1.2))+1;
        if(board.isValidField(x,y))
            boardGuiListener.onClicked(x,y);
    }

    @Override
    public void mousePressed(MouseEvent mE) {}

    @Override
    public void mouseReleased(MouseEvent mE) {}

    @Override
    public void mouseEntered(MouseEvent mE) {}

    @Override
    public void mouseExited(MouseEvent mE) {}

    public void setListener(BoardGuiListener boardGuiListener){
        this.boardGuiListener = boardGuiListener;
    }


    public interface BoardGuiListener {
        void onClicked(int x, int y);
    }

}
