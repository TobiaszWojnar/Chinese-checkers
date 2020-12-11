package gui.board;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EnumMap;

public class ChineseCheckersBoardGUI extends JPanel implements MouseListener {
    private Board board;
    private final Color backgroundColor = new Color(238,238,238);
    private final int pawnSize = 35;
    private final int xOffset = 50;
    EnumMap<Field, Color> colors;
    private BoardGuiListener boardGuiListener;

    public ChineseCheckersBoardGUI (Board board){
        this.board=board;
        colors = new EnumMap<>(Field.class);

        colors.put(Field.Empty, Color.GRAY);
        colors.put(Field.Player1, Color.YELLOW);
        colors.put(Field.Player2, Color.RED);
        colors.put(Field.Player3, Color.GREEN);
        colors.put(Field.Player4, Color.BLUE);
        colors.put(Field.Player5, Color.ORANGE);
        colors.put(Field.Player6, Color.PINK);
        colors.put(Field.Possible, Color.WHITE);

        addMouseListener(this);
    }

    public void paint(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getX(), getY());
        g.setColor(Color.BLACK);

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
        g.setColor(colors.get(board.getBoard()[y][x]));

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
