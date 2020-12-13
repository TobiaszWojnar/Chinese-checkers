package server;

import board.Field;
import board.corners.*;

import java.util.HashMap;

public class CornerMap extends HashMap<Field, Corner> {

    public CornerMap(int num) {
        switch (num) {
            case 2:
                this.put(Field.Player1, LowerCorner.getInstance());
                this.put(Field.Player2, UpperCorner.getInstance());
            case 3:
                this.put(Field.Player1, LowerCorner.getInstance());
                this.put(Field.Player2, LowerRightCorner.getInstance());
                this.put(Field.Player3, LowerLeftCorner.getInstance());
            case 4:
                this.put(Field.Player1, LowerRightCorner.getInstance());
                this.put(Field.Player2, LowerLeftCorner.getInstance());
                this.put(Field.Player3, UpperLeftCorner.getInstance());
                this.put(Field.Player4, UpperRightCorner.getInstance());
            case 6:
                this.put(Field.Player1, LowerCorner.getInstance());
                this.put(Field.Player2, LowerLeftCorner.getInstance());
                this.put(Field.Player3, UpperLeftCorner.getInstance());
                this.put(Field.Player4, UpperCorner.getInstance());
                this.put(Field.Player5, UpperRightCorner.getInstance());
                this.put(Field.Player6, LowerRightCorner.getInstance());
        }
    }
}
