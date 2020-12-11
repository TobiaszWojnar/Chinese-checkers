package gui.board;

import board.Board;
import board.ChineseCheckersBoard;
import gui.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChineseCheckersBoardGUI extends JPanel implements MouseListener {
    private Board board;
    private final int pawnSize = 35;
    private final int xOffset = 55;
    private final ColorManager colorManager;
    private BoardGuiListener boardGuiListener;

    public ChineseCheckersBoardGUI (Board board, ColorManager colorManager){
        this.board=board;
        this.colorManager=colorManager;

        addMouseListener(this);
    }

    public void paint(Graphics g){
        g.setColor(colorManager.getBackgroundColor());
        System.out.println(g.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        for(int y = 0; y<board.getBoard().length;y++){//TODO do we need methods board.getWidth() board.getHeight()?
            for(int x = 0; x<board.getBoard()[0].length;x++) {
                if(isValidField(x,y))
                    paintElement(g,x,y);
            }
        }
    }

    private boolean isValidField(int x, int y){
        if((x+y)%2==0){
            switch (y){
                case 0:
                case 16:
                    return x == 12;
                case 1:
                case 15:
                    return x > 10 && x < 14;
                case 2:
                case 14:
                    return x > 9 && x < 15;
                case 3:
                case 13:
                    return x > 8 && x < 16;
                case 4:
                case 12:
                    return true;
                case 5:
                case 11:
                    return x > 0 && x < 24;
                case 6:
                case 10:
                    return x > 1 && x < 23;
                case 7:
                case 9:
                    return x > 2 && x < 22;
                case 8:
                    return x > 3 && x < 21;
                default:
                    return false;
            }
        }
        return false;
    }

    private void paintElement (Graphics g, int x, int y){
        g.setColor(colorManager.getMappedColor(board.getBoard()[y][x]));

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
        if(isValidField(x,y))
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
