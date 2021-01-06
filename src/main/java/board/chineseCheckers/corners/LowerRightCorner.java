package board.chineseCheckers.corners;

import board.IntPoint;

import java.util.ArrayList;

/**
 * Singleton of lower right corner of the board
 */
public class LowerRightCorner extends Corner {

    private static LowerRightCorner instance = null;

    /**
     * Singleton instance getter
     * @return instance
     */
    public static LowerRightCorner getInstance() {
        if (instance == null) {
            throw new AssertionError("Call init first");
        }
        return instance;
    }

    private LowerRightCorner(int n) {
        points = new ArrayList<>();
        for (int i = 2 * n + 1; i <= 3 * n; i++) {
            for (int j = 3 * n - i; j <=  i - n - 2; j += 2) {
                points.add(new IntPoint(j + 4 * n + 2, i));
            }
        }
    }

    /**
     * Init method for different board sizes
     * @param n board size
     */
    public synchronized static void init(int n) {
        /*if (instance != null) {
            throw new AssertionError("Already initalized");
        }*/
        instance = new LowerRightCorner(n);
    }
}
