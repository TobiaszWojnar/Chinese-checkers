package board.chineseCheckers.corners;

import board.IntPoint;

import java.util.ArrayList;

public class UpperLeftCorner extends Corner {

    private static UpperLeftCorner instance = null;

    public static UpperLeftCorner getInstance() {
        if (instance == null) {
            throw new AssertionError("Call init first");
        }
        return instance;
    }

    private UpperLeftCorner(int n) {
        points = new ArrayList<>();
        for (int i = n; i < 2 * n; i++) {
            for (int j = i - n; j <=  3 * n - 2 - i; j += 2) {
                points.add(new IntPoint(j, i));
            }
        }
    }

    public synchronized static void init(int n) {
        /*if (instance != null) {
            throw new AssertionError("Already initalized");
        }*/
        instance = new UpperLeftCorner(n);
    }
}
