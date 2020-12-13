package board.corners;

import board.IntPoint;

import java.util.ArrayList;
import java.util.function.Consumer;

@SuppressWarnings("FieldMayBeFinal")
public class UpperLeftCorner extends Corner {
    private static UpperLeftCorner instance = null;

    private UpperLeftCorner() {
        points = new ArrayList<>();
        points.add(new IntPoint(3, 7));
        points.add(new IntPoint(2, 6));
        points.add(new IntPoint(4, 6));
        points.add(new IntPoint(1, 5));
        points.add(new IntPoint(3, 5));
        points.add(new IntPoint(5, 5));
        points.add(new IntPoint(0, 4));
        points.add(new IntPoint(2, 4));
        points.add(new IntPoint(4, 4));
        points.add(new IntPoint(6, 4));
    }

    public static UpperLeftCorner getInstance() {
        if (instance == null) {
            return new UpperLeftCorner();
        }
        return instance;
    }
}
