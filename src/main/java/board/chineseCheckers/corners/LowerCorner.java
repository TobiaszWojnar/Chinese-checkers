package board.chineseCheckers.corners;

import board.IntPoint;

import java.util.ArrayList;

public class LowerCorner extends Corner {

    private static LowerCorner instance = null;

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

    public synchronized static void init(int n) {
        /*if (instance != null) {
            throw new AssertionError("Already initalized");
        }*/
        instance = new LowerCorner(n);
    }
}
