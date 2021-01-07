package board.chineseCheckers.corners;

import board.IntPoint;

import java.util.ArrayList;

/**
 * Singleton of lower corner of the board
 */
public class LowerCorner extends Corner {

    private static LowerCorner instance = null;

    /**
     * Singleton instance getter
     *
     * @return instance
     */
    public static LowerCorner getInstance() {
        if (instance == null) {
            throw new AssertionError("Call init first");
        }
        return instance;
    }

    private LowerCorner(int n) {
        points = new ArrayList<>();
        for (int i = 4 * n; i > 3 * n; i--) {
            for (int j = i - n; j <= 7 * n - i; j += 2) {
                points.add(new IntPoint(j, i));
            }
        }
    }

    /**
     * Init method for different board sizes
     *
     * @param n board size
     */
    public synchronized static void init(int n) {
        /*if (instance != null) {
            throw new AssertionError("Already initalized");
        }*/
        instance = new LowerCorner(n);
    }
}
