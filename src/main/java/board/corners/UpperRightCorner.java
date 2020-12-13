package board.corners;

import board.IntPoint;

import java.util.ArrayList;
import java.util.function.Consumer;

@SuppressWarnings("FieldMayBeFinal")
public class UpperRightCorner extends Corner {
    private static UpperRightCorner instance = null;

    private UpperRightCorner() {
        points = new ArrayList<>();
        points.add(new IntPoint(21, 7));
        points.add(new IntPoint(20, 6));
        points.add(new IntPoint(22, 6));
        points.add(new IntPoint(19, 5));
        points.add(new IntPoint(21, 5));
        points.add(new IntPoint(23, 5));
        points.add(new IntPoint(18, 4));
        points.add(new IntPoint(20, 4));
        points.add(new IntPoint(22, 4));
        points.add(new IntPoint(24, 4));
    }

    public static UpperRightCorner getInstance() {
        if (instance == null) {
            return new UpperRightCorner();
        }
        return instance;
    }
}
