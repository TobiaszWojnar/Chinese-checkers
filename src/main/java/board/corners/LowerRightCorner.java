package board.corners;

import board.IntPoint;

import java.util.ArrayList;
import java.util.function.Consumer;

@SuppressWarnings("FieldMayBeFinal")
public class LowerRightCorner extends Corner {
    private static LowerRightCorner instance = null;

    private LowerRightCorner() {
        points = new ArrayList<>();
        points.add(new IntPoint(21, 9));
        points.add(new IntPoint(20, 10));
        points.add(new IntPoint(22, 10));
        points.add(new IntPoint(19, 11));
        points.add(new IntPoint(21, 11));
        points.add(new IntPoint(23, 11));
        points.add(new IntPoint(18, 12));
        points.add(new IntPoint(20, 12));
        points.add(new IntPoint(22, 12));
        points.add(new IntPoint(24, 12));
    }

    public static LowerRightCorner getInstance() {
        if (instance == null) {
            return new LowerRightCorner();
        }
        return instance;
    }
}
