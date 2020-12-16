package board.corners;

import board.IntPoint;

import java.util.ArrayList;

@SuppressWarnings("FieldMayBeFinal")
public class UpperCorner extends Corner {
    private static UpperCorner instance = null;

    private UpperCorner() {
        points = new ArrayList<>();
        points.add(new IntPoint(12, 0));
        points.add(new IntPoint(11, 1));
        points.add(new IntPoint(13, 1));
        points.add(new IntPoint(10, 2));
        points.add(new IntPoint(12, 2));
        points.add(new IntPoint(14, 2));
        points.add(new IntPoint(9, 3));
        points.add(new IntPoint(11, 3));
        points.add(new IntPoint(13, 3));
        points.add(new IntPoint(15, 3));
    }

    public static UpperCorner getInstance() {
        if (instance == null) {
            return new UpperCorner();
        }
        return instance;
    }
}
