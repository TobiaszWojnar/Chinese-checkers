package board.chineseCheckers.corners;

import board.IntPoint;

import java.util.ArrayList;

public class UpperCorner extends Corner {

    private static UpperCorner instance = null;

    public static UpperCorner getInstance() {
        if (instance == null) {
            throw new AssertionError("Call init first");
        }
        return instance;
    }

    private UpperCorner(int n) {
        points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 3 * n - i; j <=  3 * n + i; j += 2) {
                points.add(new IntPoint(j, i));
            }
        }
    }

    public synchronized static void init(int n) {
        /*if (instance != null) {
            throw new AssertionError("Already initalized");
        }*/
        instance = new UpperCorner(n);
    }
}
