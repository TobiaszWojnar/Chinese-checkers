package board.corners;

import board.IntPoint;

import java.util.ArrayList;
import java.util.function.Consumer;

@SuppressWarnings("FieldMayBeFinal")
public class LowerLeftCorner extends Corner {
    private static LowerLeftCorner instance = null;

    private LowerLeftCorner() {
        points = new ArrayList<>();
        points.add(new IntPoint(3, 9));
        points.add(new IntPoint(2, 10));
        points.add(new IntPoint(4, 10));
        points.add(new IntPoint(1, 11));
        points.add(new IntPoint(3, 11));
        points.add(new IntPoint(5, 11));
        points.add(new IntPoint(0, 12));
        points.add(new IntPoint(2, 12));
        points.add(new IntPoint(4, 12));
        points.add(new IntPoint(6, 12));
    }

    public static LowerLeftCorner getInstance() {
        if (instance == null) {
            return new LowerLeftCorner();
        }
        return instance;
    }
}
