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
    private final ColorManager colorManager;
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
        boardUpdate(board);

        addMouseListener(this);
    }

    /**
     * Prepares board for displaying.
     *
     * @param board Contains information of current board situation and what fields are valid
     * @param colorManager specifies colors of fields and pawns
     */
    public ChineseCheckersBoardGUI(ChineseCheckersBoard board, ColorManager colorManager) {
        this.colorManager = colorManager;
        angle = 0;
        boardUpdate(board);

        addMouseListener(this);
    }

    /**
     * Clears board and repaints all pawns and fields.
     *
     * @param g Graphics
     */
    public void paint(Graphics g) {

        g.setColor(colorManager.getBackgroundColor());
        System.out.println(g.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g;
        for (MyPair p : fieldList) {
            g2.setColor(colorManager.getMappedColor(board.getField(p.getX(), p.getY())));
            g2.draw(p.getShape());
            g2.fill(p.getShape());
        }
    }

    /**
     * Updates where pawns are and theirs size.
     *
     * @param board current board passed from GameGUI
     */
    public void boardUpdate(ChineseCheckersBoard board) {
        boardUpdate(board, new Dimension(800,800));
    }

    /**
     * Updates where pawns are and theirs size.
     *
     * @param board current board passed from GameGUI
     * @param dimension current dimension of GameGUI
     */
    public void boardUpdate(ChineseCheckersBoard board, Dimension dimension) {
        this.board = board;
        double smallerDimension = Math.min(dimension.getHeight(),dimension.getWidth());

        double xOffset = ((smallerDimension * 17) / (12 * (double) board.getHeight()));
        double pawnSize = (smallerDimension * 17) / (19 * (double) board.getHeight());
        double ox = (xOffset + 3 * board.getN() * pawnSize * 0.6);
        double oy = 2 * board.getN() * pawnSize;

        fieldList.clear();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board.isValidField(x, y)) {
                    double xScalar = xOffset + x * pawnSize * 0.6 - ox;
                    double qx = (ox + Math.cos(angle * Math.PI / 180) * (xScalar) - Math.sin(angle * Math.PI / 180) * (y * pawnSize - oy));
                    double qy = (oy + Math.sin(angle * Math.PI / 180) * (xScalar) + Math.cos(angle * Math.PI / 180) * (y * pawnSize - oy));
                    fieldList.add(new MyPair(
                            new Ellipse2D.Double(qx, qy, pawnSize, pawnSize),
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
     * Setter for changing board orientation.
     *
     * @param angle of rotation in degrees.
     */
    public void setAngle(int angle) {
        this.angle=angle;
    }

    /**
     * Rotates board by 60 degrees
     */
    public void rotate() {
        angle = (angle + 60) % 360;
        boardUpdate(board);
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
    private static class MyPair {
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
