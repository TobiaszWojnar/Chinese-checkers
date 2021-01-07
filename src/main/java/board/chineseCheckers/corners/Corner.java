package board.chineseCheckers.corners;

import board.IntPoint;

import java.util.List;

/**
 * Abstract class for creating singletons of board conrners for Chinese Checkers game
 *
 * @see LowerCorner
 * @see LowerLeftCorner
 * @see LowerRightCorner
 * @see UpperCorner
 * @see UpperLeftCorner
 * @see UpperRightCorner
 */
public abstract class Corner {
    public List<IntPoint> points;

    /**
     * Checks if given point is in the corner
     *
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return true if corner contains the point false otherwise
     */
    public boolean has(int x, int y) {
        for (IntPoint p : points) {
            if (p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
