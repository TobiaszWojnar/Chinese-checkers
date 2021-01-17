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

/**
 * JPanel for displaying games
 */
public class ChineseCheckersBoardGUI extends JPanel implements MouseListener {
    private ChineseCheckersBoard board;
    private ColorManager colorManager;
    private BoardGuiListener boardGuiListener;
    private final List<MyPair> fieldList = new LinkedList<>();
    private int angle;

    /**
     * Prepares board for displaying.
     *
     * @param board Contains information of current board situation and what fields are valid
     * @param colorManager specifies colors of fields and pawns
     * @param initialAngle of board rotation in degrees
     */
    public ChineseCheckersBoardGUI(ChineseCheckersBoard board, ColorManager colorManager, int initialAngle) {
        this.colorManager = colorManager;
        angle = initialAngle;
        this.board=board;

        addMouseListener(this);
    }

    /**
     * Prepares board for displaying.
     *  @param board Contains information of current board situation and what fields are valid
     * @param colorManager specifies colors of fields and pawns
     */
    public ChineseCheckersBoardGUI(ChineseCheckersBoard board, ColorManager colorManager) {
        new ChineseCheckersBoardGUI(board, colorManager, 0);
    }

    /**
     * Clears board and repaints all pawns and fields.
     *
     * @param g Graphics
     */
    public void paint(Graphics g) {
        g.setColor(colorManager.getBackgroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2 = (Graphics2D) g;
        for (MyPair p : fieldList) {
            g2.setColor(colorManager.getMappedColor(board.getField(p.getX(), p.getY())));
            g2.draw(p.getShape());
            g2.fill(p.getShape());
        }
    }


    public void update(ChineseCheckersBoard board ) {
        this.board = board;
        recalculate();
    }

    /**
     * Rotates board by 60 degrees
     */
    public void rotate() {
        angle = (angle + 60) % 360;
        recalculate();
    }

    public void update(Dimension dimension){
        setSize(dimension);
        recalculate();
    }

    public void recalculate(){

        double smallerDimension = Math.min(getHeight(),getWidth());
        double separationXFactor = 2/Math.sqrt(3);
                //Math.sqrt(3);
                //Math.sin(Math.toRadians(60))*2;
        double pawnSize = (smallerDimension/((4*board.getN()+1)));

        double marginX=(getWidth()-(3*board.getN()+1)*pawnSize*separationXFactor)/2;
        double marginY=(getHeight()-(4*board.getN()+1)*pawnSize)/2;

        double centerX=pawnSize*3*board.getN()*separationXFactor/2;
        double centerY=pawnSize*2*board.getN();

        fieldList.clear();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board.isValidField(x, y)) {

                    double v = (x * pawnSize * separationXFactor) / 2 - centerX;
                    double rotatedX =  marginX + centerX + Math.cos(Math.toRadians(angle))* v
                            - Math.sin(Math.toRadians(angle))*(y*pawnSize - centerY);
                    double rotatedY = marginY + centerY + Math.sin(Math.toRadians(angle))* v
                            + Math.cos(Math.toRadians(angle))*(y*pawnSize - centerY);

                    //qx = ox + math.cos(angle) * (px - ox) - math.sin(angle) * (py - oy)
                    //qy = oy + math.sin(angle) * (px - ox) + math.cos(angle) * (py - oy)
                    fieldList.add(new MyPair(
                            new Ellipse2D.Double(rotatedX, rotatedY, pawnSize, pawnSize),
                            new Point2D.Float(x, y)));
                }
            }
        }
        repaint();
    }




    /**
     * Checks is click was inside field or pawn.
     *
     * @param mE click
     */
    @Override
    public void mouseClicked(MouseEvent mE) {
        System.out.println(mE.getX()+" "+mE.getY());
        for (MyPair p : fieldList) {
            if (p.getShape().contains(mE.getPoint())) {
                boardGuiListener.onClicked(p.getX(), p.getY());
            }
        }
    }

    /**
     * Unused
     *
     * @param mE Unused
     */
    @Override
    public void mousePressed(MouseEvent mE) {
    }

    /**
     * Unused
     *
     * @param mE Unused
     */
    @Override
    public void mouseReleased(MouseEvent mE) {
    }

    /**
     * Unused
     *
     * @param mE Unused
     */
    @Override
    public void mouseEntered(MouseEvent mE) {
    }

    /**
     * Unused
     *
     * @param mE Unused
     */
    @Override
    public void mouseExited(MouseEvent mE) {
    }

    /**
     * Listener enabling others to register clicks on board.
     *
     * @param boardGuiListener Listener enabling others to register clicks on board.
     */
    public void setListener(BoardGuiListener boardGuiListener) {
        this.boardGuiListener = boardGuiListener;
    }


    /**
     * Listener enabling others to register clicks on board.
     */
    public interface BoardGuiListener {
        void onClicked(int x, int y);
    }

    /**
     * Supportive class for painting board.
     * Enables separation of each field from its original location, like rotation.
     */
    protected static class MyPair {
        private final Shape shape;
        private final Point2D point;

        /**
         * Constructor
         * @param s In practice only Elipse2D
         * @param p original location on the board
         */
        public MyPair(Shape s, Point2D p) {
            shape = s;
            point = p;
        }

        /**
         * Returns Shape
         *
         * @return Shape
         */
        public Shape getShape() {
            return shape;
        }

        /**
         * Returns original location on the board.
         *
         * @return original location on the board.
         */
        public Point2D getPoint() {
            return point;
        }

        /**
         * Returns x coordinate of original  location on the board.
         *
         * @return Returns x coordinate of original  location on the board.
         */
        public int getX() {
            return (int) point.getX();
        }

        /**
         * Returns y coordinate of original  location on the board.
         *
         * @return Returns y coordinate of original  location on the board.
         */
        public int getY() {
            return (int) point.getY();
        }
    }
}
