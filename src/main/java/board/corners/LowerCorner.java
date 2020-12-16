package board.corners;

import board.IntPoint;

import java.util.ArrayList;

@SuppressWarnings("FieldMayBeFinal")
public class LowerCorner extends Corner {
    private static LowerCorner instance = null;

    private LowerCorner() {
        points = new ArrayList<>();
        points.add(new IntPoint(12, 16));
        points.add(new IntPoint(11, 15));
        points.add(new IntPoint(13, 15));
        points.add(new IntPoint(10, 14));
        points.add(new IntPoint(12, 14));
        points.add(new IntPoint(14, 14));
        points.add(new IntPoint(9, 13));
        points.add(new IntPoint(11, 13));
        points.add(new IntPoint(13, 13));
        points.add(new IntPoint(15, 13));
    }

    public static LowerCorner getInstance() {
        if (instance == null) {
            return new LowerCorner();
        }
        return instance;
    }
}
