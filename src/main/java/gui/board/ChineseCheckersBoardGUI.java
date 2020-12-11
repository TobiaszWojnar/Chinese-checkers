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
    private int canvasHeight;
    private int canvasWidth;
    private int pawnSize = 10;
    EnumMap<Field, Color> colors;
    private Listener listener;

    public ChineseCheckersBoardGUI (Board board){
        this.board=board;
        colors = new EnumMap<>(Field.class);

        colors.put(Field.Empty, Color.GRAY);
        colors.put(Field.Player1, Color.YELLOW);
        colors.put(Field.Player2, Color.RED);
        colors.put(Field.Player3, Color.GREEN);
        colors.put(Field.Player4, Color.BLUE);
        colors.put(Field.Player5, Color.ORANGE);
        colors.put(Field.Player6, Color.WHITE);
        colors.put(Field.Possible, Color.WHITE);
    }

    public void paint(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        g.setColor(Color.BLACK);

        for(int y = 0; y<board.getBoard().length;y++){//TODO do we need methods board.getWidth() board.getHeight()
            for(int x = 0; x<board.getBoard()[0].length;x++) {
                paintElement(g,x,y);
            }
        }
        //TODO paint all fields
        //TODO cover not 'fields' between fields
        //TODO cover external part
    }

    private void paintElement (Graphics g, int x, int y){
        g.setColor(colors.get(board.getBoard()[y][x]));
        g.fillOval(x*pawnSize,y*pawnSize,pawnSize,pawnSize);
    }

    public void boardUpdate (ChineseCheckersBoard board){
        this.board=board;
    }

    @Override
    public void mouseClicked(MouseEvent mE) {
        //TODO wyrzuca wskazanie na konkretne pole
        //if()
        listener.onClicked(mE.getX(),mE.getY());
    }

    @Override
    public void mousePressed(MouseEvent mE) {}

    @Override
    public void mouseReleased(MouseEvent mE) {}

    @Override
    public void mouseEntered(MouseEvent mE) {}

    @Override
    public void mouseExited(MouseEvent mE) {}

    public void setListener(Listener listener){
        this.listener=listener;
    }

    public interface Listener {
        void onClicked(int x, int y);
    }
}
