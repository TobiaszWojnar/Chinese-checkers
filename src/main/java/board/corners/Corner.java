package board.corners;

import board.IntPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Corner {
    public List<IntPoint> points;

    public boolean has(int x, int y) {
        for (IntPoint p : points) {
            if (p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
