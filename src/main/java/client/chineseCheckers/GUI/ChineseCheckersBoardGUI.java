package client.chineseCheckers.GUI;

import board.chineseCheckers.ChineseCheckersBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class ChineseCheckersBoardGUI extends JPanel implements MouseListener {
    private ChineseCheckersBoard board;
    private final ColorManager colorManager;
    private BoardGuiListener boardGuiListener;
    private final List<MyPair> fieldList = new LinkedList<>();

    public ChineseCheckersBoardGUI (ChineseCheckersBoard board, ColorManager colorManager){
        this.colorManager = colorManager;
        boardUpdate(board);



        addMouseListener(this);
    }

    public void paint(Graphics g){//TODO state, for shifting board

        g.setColor(colorManager.getBackgroundColor());
        System.out.println(g.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g;
        for(MyPair p: fieldList){
            g2.setColor(colorManager.getMappedColor(board.getField(p.getX(), p.getY())));
            g2.draw(p.getShape());
            g2.fill(p.getShape());
        }
    }

    public void boardUpdate (ChineseCheckersBoard board){
        this.board=board;

        int xOffset = (int) ((800 * 17) / (12 * board.getHeight()));
        int pawnSize = (int) ((800 * 17) / (19 * board.getHeight()));

        fieldList.clear();
        for(int y = 0; y<board.getHeight();y++){
            for(int x = 0; x<board.getWidth();x++) {
                if(board.isValidField(x,y)){
                    //TODO change values to change with board size

                    fieldList.add(new MyPair(
                            new Ellipse2D.Float((float) (xOffset + x * pawnSize * 0.6), y * pawnSize, pawnSize, pawnSize),
                            new Point2D.Float(x, y)));
                }
            }
        }
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent mE) {
        for(MyPair p: fieldList){
            if(p.getShape().contains(mE.getPoint())) {
                boardGuiListener.onClicked(p.getX(),p.getY());
            }
        }
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

    public void gameResize(int h, int w) {
        repaint();
    }


    public interface BoardGuiListener {
        void onClicked(int x, int y);
    }

    public static class MyPair {
        private final Shape shape;
        private final Point2D point;

        public MyPair(Shape s, Point2D p) {
            shape = s;
            point = p;
        }

        public Shape getShape() {
            return shape;
        }
        public Point2D getPoint() {
            return point;
        }
        public int getX(){
            return (int) point.getX();
        }
        public int getY(){
            return (int) point.getY();
        }
    }
}
